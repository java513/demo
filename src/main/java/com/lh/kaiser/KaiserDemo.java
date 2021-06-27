package com.lh.kaiser;

import java.lang.reflect.Method;

/**
 * 凯撒加密
 */
public class KaiserDemo {
    public static void main(String[] args) {
        String msg = "Hello World!";
        String encryptKaiser = encryptKaiser(msg);
        System.out.println("加密后的密文=="+encryptKaiser);

        System.out.println("解密后的密文=="+decryptKaiser(encryptKaiser));
    }

    /**
     * @description: 加密
     * @params:[input]
     * @return: java.lang.String
     * @author: lh
     * @date: 2021/6/26
     */
    private static String encryptKaiser(String input){
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            b = b+3;
            char newchar = (char)b;
          sb.append(newchar);
        }
        return sb.toString();
    }

    /**
     * @description: 解密
     * @params:[input]
     * @return: java.lang.String
     * @author: lh
     * @date: 2021/6/26
     */
    private static String decryptKaiser(String input){
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            int b = aChar;
            b = b-3;
            char newchar = (char)b;
            sb.append(newchar);
        }
        return sb.toString();
    }
}
