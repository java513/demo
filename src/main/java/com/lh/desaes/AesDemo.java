package com.lh.desaes;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @program: encrypt-decrypt
 * @description: aes加解密
 * @author: lh
 * @date: 2021-06-27 10:11
 **/
public class AesDemo {
    public static void main(String[] args) throws Exception {
        String msg = "雄霸天下！";
        //使用aes加密，密钥必须是16个字节
        String key = "1234567812345678";
        //算法
        String information = "AES";
        //加密类型
        String algorithm = "AES";
        String encode = encryptAES(msg, key, information, algorithm);
        System.out.println("加密后的数据:"+encode);
        String decryptDES = decryptAES(encode, key, information, algorithm);
        System.out.println("解密后的数据:"+decryptDES);
    }

    /**
     * @description: des加密
     * @params:[msg, key, information, algorithm]
     * @return: java.lang.String
     * @author: lh
     * @date: 2021/6/26
     */
    private static String encryptAES(String msg, String key, String information, String algorithm) throws Exception {
        //创建加密对象
        Cipher cipher = Cipher.getInstance(information);
        //创建加密规则（1，key的字节，2加密的类型）
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(),algorithm);
        //进行加密初始化（1，表示模式，2加密规则）
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
        //调用加密方法
        byte[] bytes = cipher.doFinal(msg.getBytes());
        //创建base64对象
        return Base64.encode(bytes);
    }

    /**
     * 解密
     * @param msg
     * @param key
     * @param information
     * @param algorithm
     * @return
     * @throws Exception
     */
    private static String decryptAES(String msg, String key, String information, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(information);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] decode = Base64.decode(msg.getBytes());
        //解密
        byte[] bytes = cipher.doFinal(decode);
        return new String(bytes);
    }
}
