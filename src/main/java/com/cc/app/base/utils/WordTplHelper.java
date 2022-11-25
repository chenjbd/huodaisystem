package com.cc.app.base.utils;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * word模板工具类，用于根据模板生成word文件
 * 基于JAVA POI XWPF 组件
 */
@Component
public class WordTplHelper {

    private Logger logger = LoggerFactory.getLogger(WordTplHelper.class);

    private static WordTplHelper instance;

    private WordTplHelper() {
    }

    public static synchronized WordTplHelper getInstance() {
        if (instance == null) {
            instance = SpringUtil.getBean(WordTplHelper.class);
            //instance = new WordTplHelper();//运行main时使用
        }
        return instance;
    }

    /**
     * 输出word文档，参数对象key的值需要与WORD占位符一致，如${title}
     * @param tplPath   模板文件路径
     * @param textData  文本参数替换
     * @param tableData 表格参数替换
     * @param picData   图片参数替换
     * @param tarPath   文档输出路径
     * @return
     */
    public String outWord(String tplPath, Map<String,Object> textData,
                          Map<String,Object> tableData,
                          Map<String,Object> picData, String tarPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try{
            fis = new FileInputStream(tplPath);
            XWPFDocument document = new XWPFDocument(fis);
            //替换模板内容
            replaceText(document, textData);
            replaceTable(document, tableData);
            replacePic(document, picData);
            //输出文档
            File tar = new File(tarPath);
            if(tar.exists()){
                tar.createNewFile();
            }
            fos = new FileOutputStream(tar);
            document.write(fos);
        }catch (Exception e){
            logger.error("输出文档失败", e);
        }finally {
            if(fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {}
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {}
            }
        }
        return "";
    }

    /**
     * 输出word文档，参数对象key的值需要与WORD占位符一致，如${title}
     * @param inputStream   模板文件流
     * @param textData  文本参数替换
     * @param tableData 表格参数替换
     * @param picData   图片参数替换
     * @param tarFile   文档输出路径
     * @return
     */
    public String outWordFromStream(InputStream inputStream, Map<String,Object> textData,
                                    Map<String,Object> tableData,
                                    Map<String,Object> picData, File tarFile){
        FileOutputStream fos = null;
        try{
            XWPFDocument document = new XWPFDocument(inputStream);
            //替换模板内容
            replaceText(document, textData);
            replaceTable(document, tableData);
            replacePic(document, picData);

            //输出文档
            if(tarFile.exists()){
                tarFile.createNewFile();
            }
            File dir=tarFile.getParentFile();

            fos = new FileOutputStream(tarFile);
            document.write(fos);
            return tarFile.getCanonicalPath().replace(dir.getParentFile().getCanonicalPath(),"");
        }catch (Exception e){
            logger.error("输出文档失败", e);
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {}
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {}
            }
        }
        return "";
    }

    /**
     * 替换文本
     * @param document
     * @param textData
     */
    private void replaceText(XWPFDocument document, Map<String, Object> textData) {
        if(textData == null){
            return;
        }
        List<XWPFParagraph> paragraphs = document.getParagraphs();//获取文档所有段落
        for(XWPFParagraph paragraph : paragraphs){
            String text = paragraph.getText();//获取段落所有文本
            if(!checkText(text)){//是否包含参数字符
                continue;
            }
            List<XWPFRun> runs = paragraph.getRuns();
            for(XWPFRun run : runs){
                if(textData.containsKey(run.toString())){
                    logger.debug(run.toString());//${key}
                    String value = (String) textData.get(run.toString());
                    run.setText(value, 0);
                }
            }
        }
    }

    /**
     * 替换图片
     * @param document
     * @param picData
     */
    private void replacePic(XWPFDocument document, Map<String, Object> picData) {
        if(picData == null){
            return;
        }
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for(XWPFParagraph paragraph : paragraphs){
            String text = paragraph.getText();
            if(!checkText(text)){
                continue;
            }
            List<XWPFRun> runs = paragraph.getRuns();
            for(XWPFRun run : runs){
                if(picData.containsKey(run.toString())){
                    String value = (String) picData.get(run.toString());
                    run.setText("", 0);
                    try{
                        FileInputStream fis = new FileInputStream(value);
                        run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, value,
                                Units.toEMU(100), Units.toEMU(100));
                    }catch (Exception e){

                    }
                }
            }
        }
    }

    /**
     * 替换表格数据
     * @param document
     * @param tableData
     */
    private void replaceTable(XWPFDocument document, Map<String, Object> tableData) {
        if(tableData == null){
            return;
        }
        List<XWPFTable> tables = document.getTables();
        for(XWPFTable table : tables){
            if(!checkText(table.getText())){
                continue;
            }
            List<XWPFTableRow> rows = table.getRows();
            eachTableRows(document, rows, tableData);
        }
    }

    /**
     * 遍历表格，替换
     * @param document
     * @param rows
     * @param tableData
     */
    private void eachTableRows(XWPFDocument document, List<XWPFTableRow> rows, Map<String, Object> tableData) {
        for(XWPFTableRow row : rows){
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                if(!checkText(cell.getText())){
                    continue;
                }
                List<XWPFParagraph> paragraphs = cell.getParagraphs();
                for(XWPFParagraph paragraph : paragraphs){
                    String text = paragraph.getText();//获取段落所有文本
                    if(!checkText(text)){//是否包含参数字符
                        continue;
                    }
                    List<XWPFRun> runs = paragraph.getRuns();
                    for(XWPFRun run : runs){
                        if(tableData.containsKey(run.toString())){
                            String value = (String) tableData.get(run.toString());
                            run.setText(value, 0);
                        }
                    }
                }
            }
        }
    }

    private Object getParamValue(String run, Map<String, Object> textData) {
        Object v = "";
        Set<Map.Entry<String,Object>> entrySet = textData.entrySet();
        for(Map.Entry<String,Object> entry : entrySet){
            if(run.contains(entry.getKey())){//${key}
                v = entry.getValue();
                break;
            }
        }
        return v;
    }

    /**
     * 检查文本中是否包含参数字符$
     * @param text
     * @return
     */
    private boolean checkText(String text) {
        boolean check = false;
        if(text.contains("$")){
            check = true;
        }
        return check;
    }

    public static void main(String[] args){
        String tplPath = "d:/tpl.docx";
        Map<String,Object> textData = new HashMap<>();
        textData.put("${title}", "常熟市民卡系统运维服务合同");
        textData.put("${xm}", "常熟市信息化发展有限公司");
        String tarPath = "d:/tar.docx";
        WordTplHelper.getInstance().outWord(tplPath, textData, null, null, tarPath);
    }
}
