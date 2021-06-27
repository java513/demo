package com.lh.digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @program: encrypt-decrypt
 * @description: 数字摘要算法 MD5
 * 数字摘要算法，为了防止篡改
 * 常见的加密算法 md5 sha1 sha256 sha512
 * @author: lh
 * @date: 2021-06-27 11:05
 **/
public class DigestDemo {

    public static void main(String[] args) throws Exception {
        //原文
        String input = "aa";
        //md5算法
        String algorithm = "MD5";
        String md5 = getDigest(input, algorithm);
        System.out.println("md5:" + md5);

        //sha1算法
        String sha1 = getDigest(input, "SHA-1");
        System.out.println("sha1:" + sha1);
        //sha256算法
        String sha256 = getDigest(input, "SHA-256");
        System.out.println("sha256:" + sha256);
        //sha512算法
        String sha516 = getDigest(input, "SHA-512");
        System.out.println("sha512:" + sha516);
    }

    /**
     * @description:获取数字摘要
     * @params:[input, algorithm]
     * @return: java.lang.StringBuilder
     * @author: lh
     * @date: 2021/6/27
     */
    private static String getDigest(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] bytes = digest.digest(input.getBytes());
        return toHex(bytes);
    }

    /**
     * @description: 转换成16进制
     * @params:[bytes]
     * @return: java.lang.StringBuilder
     * @author: lh
     * @date: 2021/6/27
     */
    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            //把密文转换成16进制
            String s = Integer.toHexString(aByte & 0xff);
            if (s.length() == 1) {
                s = "0" + s;
            }
            sb.append(s);
        }
        return sb.toString();
    }
}
