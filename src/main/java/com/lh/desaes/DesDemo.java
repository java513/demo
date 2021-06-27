package com.lh.desaes;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @program: encrypt-decrypt
 * @description: 对称加密
 * @author: lh
 * @date: 2021-06-26 17:47
 **/
public class DesDemo {
    public static void main(String[] args) throws Exception {
        String msg = "雄霸天下！";
        //使用des加密，密钥必须是8个字节
        String key = "12345678";
        //算法
        String information = "DES";
        //加密类型
        String algorithm = "DES";
        String encode = encryptDES(msg, key, information, algorithm);
        System.out.println("加密后的数据:"+encode);
        String decryptDES = decryptDES(encode, key, information, algorithm);
        System.out.println("解密后的数据:"+decryptDES);
    }

    /**
     * @description: des加密
     * @params:[msg, key, information, algorithm]
     * @return: java.lang.String
     * @author: lh
     * @date: 2021/6/26
     */
    private static String encryptDES(String msg, String key, String information, String algorithm) throws Exception {
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
    private static String decryptDES(String msg, String key, String information, String algorithm) throws Exception {
        Cipher cipher = Cipher.getInstance(information);
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), algorithm);
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] decode = Base64.decode(msg.getBytes());
        //解密
        byte[] bytes = cipher.doFinal(decode);
        return new String(bytes);
    }

    //new string() 一般在需要转码的时候用到new string()
    //toString 需要打印对象的地址值时，使用toString
}
