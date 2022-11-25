package com.cc.app.base.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密工具类
 */
public class EncryptUtil {

    /**
     * 传入文本内容，返回 SHA-256 串
     * @param data
     * @return
     */

    public static String MD5(final String data) {
        return encode(data, "MD5");
    }

    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param data
     * @return
     */

    public static String SHA256(final String data) {
        return encode(data, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param data
     * @return
     */
    public static String SHA512(final String data) {
        return encode(data, "SHA-512");
    }

    /**
     * 字符串 SHA 加密
     * @param data 待加密数据
     * @param algorithm 算法
     * @return
     */
    private static String encode(final String data, final String algorithm) {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (data != null && data.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest
                        .getInstance(algorithm);
                // 传入要加密的字符串
                messageDigest.update(data.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return strResult;
    }

    public static void main(String[] args){
        String data = "This is a testing data : 123456789";
        System.out.println(EncryptUtil.MD5(data));
        System.out.println(EncryptUtil.SHA256(data));
        System.out.println(EncryptUtil.SHA512(data));
    }

}
