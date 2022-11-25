package com.cc.app.base.utils;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * 文件上传工具类
 */
public class UploadFileUtil {

    private static Logger logger = LoggerFactory.getLogger(UploadFileUtil.class);

    /**
     * 上传工具类
     *
     * @param request HttpServletRequest请求
     * @param tarDir 上传到目标文件目录
     * @return 返回文件信息List
     */
    public static List<Map<String, String>> uploadFile(HttpServletRequest request, String tarDir) {
        String path = tarDir;
        // 把Request强转成多部件请求对象
        MultipartHttpServletRequest mh = (MultipartHttpServletRequest) request;
        // 获取文件列表
        Map<String, MultipartFile> fm = mh.getFileMap();
        List<MultipartFile> list= getMultiFileList(mh);

        List<Map<String, String>> fileList = new ArrayList<Map<String, String>>();;//返回保存文件信息
        /*Iterator<String> fileNames = mh.getFileNames();
        while (fileNames.hasNext()) {
            String file = fileNames.next();
            // 根据文件名称获取文件对象
            MultipartFile cm = mh.getFile(file);*/
        for (MultipartFile cm : list) {
            String oldfilename = "";
            String newfilename = "";
            String extendName = "";
            try {
                oldfilename = cm.getOriginalFilename();
                if(!StrUtil.isEmpty(oldfilename)){
                    //ie下文件名含有本地路径
                    int index = oldfilename.lastIndexOf("\\");
                    if(index>-1){
                        oldfilename = oldfilename.substring(index + 1);
                    }
                    int dosPos = oldfilename.lastIndexOf(".");
                    if(dosPos>-1){
                        extendName = oldfilename.substring(dosPos);
                    }
                    newfilename = UUIDGenUtil.uuid() + extendName;
                    File upfile = new File(path, newfilename);
                    File dir=upfile.getParentFile();
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    cm.transferTo(upfile);
                    logger.debug("dir path"+dir.getParent());
                    Map<String, String> fileMap = new HashMap<String, String>();
                    fileMap.put("fileName", newfilename);//文件名
                    fileMap.put("filePath", upfile.getCanonicalPath().replace(dir.getParentFile().getCanonicalPath(),""));//文件路径
                    fileList.add(fileMap);
                }
            } catch (Exception e) {
                logger.error("上传文件异常", e);
            }
            logger.debug("filename:" + oldfilename + " >> newfilename:" + path + "/" + newfilename);
        }
        return fileList;
    }

    /**
     *
     * @param request
     * @param tarDir
     * @return
     */
    public static String updateOneFile(HttpServletRequest request, String tarDir) {
        Map<String, String> file = updateSingleFile(request,tarDir);
        if (CollectionUtils.isEmpty(file)) {
            logger.info("未上传文件或文件上传失败");
            return "";
        } else {
            return file.get("filePath");
        }
    }

    /**
     * 上传工具类
     *
     * @param request HttpServletRequest请求
     * @param tarDir 上传到目标文件目录
     * @return 返回文件信息List
     */
    public static Map<String, String> updateSingleFile(HttpServletRequest request, String tarDir) {
//        String path = "";
        // 把Request强转成多部件请求对象
        MultipartHttpServletRequest mh = (MultipartHttpServletRequest) request;
        // 获取文件列表
        List<MultipartFile> list= getMultiFileList(mh);
        Map<String, String> fileMap = new HashMap<String, String>();
//        path = request.getSession().getServletContext().getRealPath(tarDir);
        for (MultipartFile cm : list) {
            String oldfilename = "";//原文件名-不带扩展名称
            String newfilename = "";
            String extendName = "";
            try {
                oldfilename = cm.getOriginalFilename();
                if(!StrUtil.isEmpty(oldfilename)){
                    //ie下文件名含有本地路径
                    int index = oldfilename.lastIndexOf("\\");
                    if(index>-1){
                        oldfilename = oldfilename.substring(index + 1);
                    }
                    int dosPos = oldfilename.lastIndexOf(".");
                    if(dosPos>-1){
                        extendName = oldfilename.substring(dosPos);
                        oldfilename = oldfilename.replace(extendName,"");
                    }
                    newfilename = UUIDGenUtil.uuid() + extendName;
                    File upfile = new File(tarDir, newfilename);
                    File dir=upfile.getParentFile();
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    cm.transferTo(upfile);
                    logger.debug("dir path"+dir.getParent());
                    fileMap.put("originFileName", oldfilename);
                    fileMap.put("fileName", newfilename);//文件名
                    fileMap.put("filePath", upfile.getCanonicalPath().replace(dir.getParentFile().getCanonicalPath(),""));//文件相对路径
                    fileMap.put("realPath", tarDir + File.separator + newfilename);
                    break;
                }
            } catch (Exception e) {
                logger.error("上传文件异常", e);
            }
            logger.debug("filename:" + oldfilename + " >> newfilename:" + tarDir + File.pathSeparator + newfilename);
        }
        return fileMap;
    }

    public static List<MultipartFile> getMultiFileList(MultipartHttpServletRequest mh){
        List<MultipartFile> filelist=new ArrayList<MultipartFile>();
        // 获取文件map集合
        MultiValueMap map =mh.getMultiFileMap();
        //filelist.addAll(map.values());
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            List<MultipartFile> files = (List<MultipartFile>) map.get(key);
            filelist.addAll(files);
            logger.debug(key);
        }
        return filelist;
    }

    public static boolean validPhotoFile(String filePath){
        File file = new File(filePath);
        return UploadFileUtil.validPhotoFile(file);
    }


    public static boolean validPhotoFile(File file){
        boolean result = false;
        ImageInputStream iis = null;
        try{
            iis = ImageIO.createImageInputStream(file);
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext()) {
                logger.error("上传文件不是图片文件");
                return result;
            }
            result = true;
            return result;
        }catch (Exception e){
            logger.error("上传文件不是图片文件", e);
            return result;
        }finally {
            try{
                if(iis!=null){
                    iis.close();
                }
            }catch (Exception e){
            }
        }
    }

    /**
     * 根据key上传文件
     *
     * @param request HttpServletRequest请求
     * @param tarDir 上传到目标文件目录
     * @return 返回文件信息List
     */
    public static List<Map<String, Object>> uploadFilesByKey(HttpServletRequest request, String key , String tarDir) {
        String path = tarDir;
        // 把Request强转成多部件请求对象
        MultipartHttpServletRequest mh = (MultipartHttpServletRequest) request;
        // 获取文件列表
        List<MultipartFile> list= getFileListByKey(mh,key);
        return uploadFiles(list.toArray(new MultipartFile[0]), tarDir);
    }

    /**
     * 根据key上传档案文件
     *
     * @param request HttpServletRequest请求
     * @param tarDir 上传到目标文件目录
     * @return 返回文件信息List
     */
    public static List<Map<String, String>> uploadArchiveByKey(HttpServletRequest request, String key , String tarDir , String archiveNo) {
        String path = tarDir;
        // 把Request强转成多部件请求对象
        MultipartHttpServletRequest mh = (MultipartHttpServletRequest) request;
        // 获取文件列表
        List<MultipartFile> list= getFileListByKey(mh,key);

        List<Map<String, String>> fileList = new ArrayList<>();;//返回保存文件信息
        for (MultipartFile cm : list) {
            String oldfilename = "";
            String newfilename = "";
            String extendName = "";
            try {
                oldfilename = cm.getOriginalFilename();
                if(!StrUtil.isEmpty(oldfilename)){
                    //ie下文件名含有本地路径
                    int index = oldfilename.lastIndexOf("\\");
                    if(index>-1){
                        oldfilename = oldfilename.substring(index + 1);
                    }
                    int dosPos = oldfilename.lastIndexOf(".");
                    if(dosPos>-1){
                        extendName = oldfilename.substring(dosPos);
                    }
                    newfilename = archiveNo + extendName;//
                    File upfile = new File(path, newfilename);
                    File dir=upfile.getParentFile();
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    cm.transferTo(upfile);
                    logger.debug("dir path"+dir.getParent());
                    Map<String, String> fileMap = new HashMap<String, String>();
                    fileMap.put("fileName", oldfilename);//文件名
                    fileMap.put("filePath", upfile.getCanonicalPath().replace(dir.getParentFile().getCanonicalPath(),""));//文件路径
                    fileList.add(fileMap);
                }
            } catch (Exception e) {
                logger.error("上传文件异常", e);
            }
            logger.debug("filename:" + oldfilename + " >> newfilename:" + path + "/" + newfilename);
        }
        return fileList;
    }

    private static List<MultipartFile> getFileListByKey(MultipartHttpServletRequest mh, String key){
        List<MultipartFile> filelist= new ArrayList<>();
        // 获取文件map集合
        MultiValueMap map =mh.getMultiFileMap();
        List<MultipartFile> files = (List<MultipartFile>) map.get(key);
        if (!CollectionUtils.isEmpty(files)) {
            filelist.addAll(files);
        }
        return filelist;
    }

    /**
     * 根据key上传文件
     *
     * @param list 请求文件列表
     * @param tarDir 上传到目标文件目录
     * @return 返回文件信息List
     */
    public static List<Map<String, Object>> uploadFiles(MultipartFile[] list, String tarDir) {
        String path = tarDir;
        List<Map<String, Object>> fileList = new ArrayList<>();;//返回保存文件信息
        for (MultipartFile cm : list) {
            String oldfilename = "";
            String newfilename = "";
            String extendName = "";
            try {
                oldfilename = cm.getOriginalFilename();
                if(!StrUtil.isEmpty(oldfilename)){
                    //ie下文件名含有本地路径
                    int index = oldfilename.lastIndexOf("\\");
                    if(index>-1){
                        oldfilename = oldfilename.substring(index + 1);
                    }
                    int dosPos = oldfilename.lastIndexOf(".");
                    if(dosPos>-1){
                        extendName = oldfilename.substring(dosPos);
                    }
                    newfilename = UUIDGenUtil.uuid() + extendName;
                    File upfile = new File(path, newfilename);
                    File dir=upfile.getParentFile();
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    cm.transferTo(upfile);
                    logger.debug("dir path"+dir.getParent());
                    Map<String, Object> fileMap = new HashMap<String, Object>();
                    fileMap.put("fileName", oldfilename);//文件名
                    fileMap.put("file", upfile);
                    fileMap.put("filePath", upfile.getCanonicalPath().replace(dir.getParentFile().getCanonicalPath(),""));//文件路径
                    fileList.add(fileMap);
                }
            } catch (Exception e) {
                logger.error("上传文件异常", e);
            }
            logger.debug("filename:" + oldfilename + " >> newfilename:" + path + "/" + newfilename);
        }
        return fileList;
    }
}
