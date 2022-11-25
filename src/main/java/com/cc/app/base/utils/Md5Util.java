package com.cc.app.base.utils;

import java.security.MessageDigest;


public class Md5Util {
    public static String encode(String str, String algorithm) {
        byte[] unencodedPassword = str.getBytes();

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            return str;
        }
        md.reset();
        md.update(unencodedPassword);
        byte[] encodedPassword = md.digest();

        StringBuffer buf = new StringBuffer();

        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                buf.append("0");
            }

            buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }

        return buf.toString();
    }

    public static String md5(String str) {
        return encode(str, "MD5");
    }

}
