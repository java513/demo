package com.lh.signature;

import com.lh.rsa.RSAdemo;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAKey;

/**
 * @program: encrypt-decrypt
 * @description: 数字签名
 * @author: lh
 * @date: 2021-06-27 15:18
 **/
public class SinatureDemo {
    public static void main(String[] args) throws Exception {
        String input  = "deamon";
        String rsa = "RSA";
        PrivateKey privateKey = RSAdemo.getPrivateKey(rsa, "b.pri");
        PublicKey publicKey = RSAdemo.getPublicKey(rsa, "a.pub");
        //生成数字签名
        String signatureData = getSignature(input,"sha256withrsa",privateKey);
        System.out.println(signatureData);

        //校验签名
        boolean b = vertifySignature(input, "sha256withrsa", publicKey, signatureData);
        System.out.println(b);
    }

    /**
     * 生成数字签名
     * @param input 原文
     * @param algorithm 算法
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    private static String getSignature(String input, String algorithm, PrivateKey privateKey)throws Exception {
        //获取签名对象
        Signature signature = Signature.getInstance(algorithm);
        //初始化签名
        signature.initSign(privateKey);
        //传入原文
        signature.update(input.getBytes());
        //开始签名
        byte[] sign = signature.sign();
        //使用base64编码
        return Base64.encode(sign);
    }

    private static boolean vertifySignature(String input,String algorithm,PublicKey publicKey,String sinatureData) throws Exception{
        //获取签名
        Signature signature = Signature.getInstance(algorithm);
        //初始化签名
        signature.initVerify(publicKey);
        //传入原文
        signature.update(input.getBytes());
        //校验签名
        return signature.verify(Base64.decode(sinatureData));
    }
}
