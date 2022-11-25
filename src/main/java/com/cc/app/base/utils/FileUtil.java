package com.cc.app.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    /**
     * 删除文件 强制删除文件
     * @param fileName
     * @param filePath
     */
    public static void deleteFile(String fileName,String filePath){
        File file = new File(filePath);
        deleteFile(file);
        logger.info("强制删除文件 >> "+fileName+" >> 成功");
    }

    /**
     * 删除文件 强制删除文件
     * @param file
     */
    public static void deleteFile(File file){
        if (!file.exists())
            return;
        if (file.isFile()) {
            boolean result = file.delete();
            int tryCount = 0;
            while (!result && tryCount++ < 10) {
                System.gc(); // 回收资源
                result = file.delete();
            }
        }
    }

    /**
     * 删除文件夹或者文件（强制删除）
     *
     * @param path
     */
    public static void deleteAllFilesOfDir(File path) {
        if (null != path) {
            deleteFile(path);
            if(path.isDirectory()){
                File[] files = path.listFiles();
                if (null != files) {
                    for (int i = 0; i < files.length; i++) {
                        deleteAllFilesOfDir(files[i]);
                    }
                }
                path.delete();
            }
        }
    }

    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return FileUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

}
