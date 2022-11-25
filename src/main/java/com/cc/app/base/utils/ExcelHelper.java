package com.cc.app.base.utils;

import com.cc.app.base.ex.ServiceExcepiton;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExcelHelper {

    private Logger logger = LoggerFactory.getLogger(ExcelHelper.class);

    private static ExcelHelper instance;

    private ExcelHelper() {
    }

    public static synchronized ExcelHelper getInstance() {
        if (instance == null) {
            instance = SpringUtil.getBean(ExcelHelper.class);
        }
        return instance;
    }

    /**
     * 从起始行，读取EXCEL中列表数据
     * @param sheet
     * @param fields
     * @param list
     * @param keyCellNum 关键列单元格，从0开始，如果为-1，则不去除空行
     * @param startRowNum 起始行
     * @return
     * @throws Exception
     */
    public void readStrRowList(Sheet sheet, String[] fields, List<Map<String, Object>> list, int keyCellNum, int startRowNum, int totalRowNum) throws Exception {
        try{
            int fieldNum = fields.length;
            Row row = null;
            Map<String,Object> rowMap = null;
            for(int i=startRowNum; i<=totalRowNum; i++){
                rowMap = new HashMap<>();
                row = sheet.getRow(i);
                if(row == null){
                    continue;
                }
                if(keyCellNum > -1){
                    Cell keyCell = row.getCell(keyCellNum);
                    String keyValue = this.getStrCellValue(keyCell);
                    //如果为空，则认定为空行
                    if(StringUtils.isBlank(keyValue)){
                        //continue;
                        break;//如果是空行，则结束读取
                    }
                }
                for(int j=0; j<fieldNum; j++){
                    Cell cell = row.getCell(j);
                    rowMap.put(fields[j],  this.getStrCellValue(cell));
                }
                list.add(rowMap);
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 从起始行，读取EXCEL中列表数据
     * @param sheet
     * @param evaluator
     * @param fields
     * @param list
     * @param keyCellNum 关键列单元格，从0开始，如果为-1，则不去除空行
     * @param startRowNum 起始行
     * @return
     * @throws Exception
     */
    public void readRowList(Sheet sheet, FormulaEvaluator evaluator, String[] fields, List<Map<String, Object>> list, int keyCellNum, int startRowNum, int totalRowNum) throws Exception {
        try{
            int fieldNum = fields.length;
            Row row = null;
            Map<String,Object> rowMap = null;
            for(int i=startRowNum; i<=totalRowNum; i++){
                rowMap = new HashMap<>();
                row = sheet.getRow(i);
                if(row == null){
                    continue;
                }
                if(keyCellNum > -1){
                    Cell keyCell = row.getCell(keyCellNum);
                    String keyValue = this.getCellValue(keyCell, evaluator);
                    //如果为空，则认定为空行
                    if(StringUtils.isBlank(keyValue)){
                        continue;
                    }
                }
                for(int j=0; j<fieldNum; j++){
                    Cell cell = row.getCell(j);
                    rowMap.put(fields[j],  this.getCellValue(cell, evaluator));
                }
                list.add(rowMap);
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     *
     * @param file
     * @param fields
     * @param list
     * @param keyCellNum 关键列单元格，从0开始，如果为-1，则不去除空行
     * @return
     * @throws Exception
     */
    public int readYsbRecord(File file, String[] fields, List<Map<String, Object>> list, int keyCellNum) throws Exception {
        FileInputStream fis = null;
        Workbook workbook = null;
        try{
            int fieldNum = fields.length;
            fis = new FileInputStream(file);
            workbook = WorkbookFactory.create(fis);

            //得到一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            //获得表头
            Row header = sheet.getRow(3);
            if(header == null){
                throw new ServiceExcepiton("EXCEL第四行没有数据");
            }
            int cellNum = header.getLastCellNum();
            if(fieldNum != cellNum){
                throw new ServiceExcepiton("EXCEL第四行表头列数不正确");
            }

            int startRowNum = 4;
            int endRowNum = sheet.getLastRowNum();//lastRowNum最后一行行号，从0开始

            if(endRowNum < startRowNum){
                throw new ServiceExcepiton("EXCEL没有数据");
            }
            this.readStrRowList(sheet, fields, list, keyCellNum, startRowNum, endRowNum);
        }catch (Exception e){
            throw e;
        }finally{
            if(workbook!=null)workbook.close();
            if(fis!=null)fis.close();
        }
        return list.size();
    }

    /**
     *
     * @param file
     * @param fields
     * @param list
     * @param keyCellNum 关键列单元格，从0开始，如果为-1，则不去除空行
     * @return
     * @throws Exception
     */
    public int readJhbRecord(File file, String[] fields, List<Map<String, Object>> list, int keyCellNum) throws Exception {
        FileInputStream fis = null;
        Workbook workbook = null;
        try{
            int fieldNum = fields.length;
            fis = new FileInputStream(file);
            workbook = WorkbookFactory.create(fis);

            //得到一个工作表
            Sheet sheet = workbook.getSheetAt(0);

            //获得表头
            Row header = sheet.getRow(3);
            if(header == null){
                throw new ServiceExcepiton("EXCEL第四行没有数据");
            }
            int cellNum = header.getLastCellNum();
            if(fieldNum != cellNum){
                throw new ServiceExcepiton("EXCEL第四行表头列数不正确");
            }

            int startRowNum = 4;
            int endRowNum = sheet.getLastRowNum();//lastRowNum最后一行行号，从0开始

            if(endRowNum < startRowNum){
                throw new ServiceExcepiton("EXCEL没有数据");
            }
            this.readStrRowList(sheet, fields, list, keyCellNum, startRowNum, endRowNum);
        }catch (Exception e){
            throw e;
        }finally{
            if(workbook!=null)workbook.close();
            if(fis!=null)fis.close();
        }
        return list.size();
    }


    private String getStrCellValue(Cell cell){
        String value = "";
        if(cell == null) {
            return value;
        }
        cell.setCellType(CellType.STRING);
        value = cell.getStringCellValue();
        value = value == null ? "" : value.trim();
        return value;
    }

    private String getCellValue(Cell cell, FormulaEvaluator evaluator){
        String value = "";
        if(cell==null){
            return value;
        }
        CellType cellType = cell.getCellTypeEnum();
        if(CellType.STRING.equals(cellType)){
            value = cell.getStringCellValue();
            value = value == null ? "" : value.trim();
        }else if(CellType.NUMERIC.equals(cellType)){
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                double numericCellValue = cell.getNumericCellValue();
                Date date = HSSFDateUtil.getJavaDate(numericCellValue);
                value = DateUtils.formatDate(date, "yyyy-MM-dd");
            } else {
                value = new DecimalFormat("0").format(cell.getNumericCellValue());
            }
        }else if(CellType.FORMULA.equals(cellType)){
            CellValue formulaCellValue = evaluator.evaluate(cell);
            if(formulaCellValue==null){
                value = "";
            }else{
                CellType formulaCellType = formulaCellValue.getCellTypeEnum();
                if(CellType.STRING.equals(formulaCellType)){
                    value = formulaCellValue.getStringValue();
                }else {
                    value = "";
                }
            }
        }else{
            value = "";
        }
        return value;
    }
}
