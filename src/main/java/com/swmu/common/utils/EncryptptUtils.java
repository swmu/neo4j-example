package com.swmu.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author seven.mu
 * @version 1.0
 * @description 加密、解密公共类
 * @date Create in 2018/12/13 18:01
 */
public class EncryptptUtils {
    /**
     * MD5加密
     *
     * @param [str]
     * @return java.lang.String
     * @author seven.mu
     * @date Create in 2018/12/18 12:52
     */
    public static String md5Encrypt(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update((str).getBytes("UTF-8"));
        byte[] b = md5.digest();

        int i;
        StringBuffer buf = new StringBuffer("");

        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString().toUpperCase();
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(md5Encrypt("123456"));
        System.out.println(UUID.randomUUID());
    }


}
