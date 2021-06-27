package com.lh.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @program: encrypt-decrypt
 * @description: 非对称加密，ras算法
 * @author: lh
 * @date: 2021-06-27 12:42
 **/
public class RSAdemo {
    public static void main(String[] args) throws Exception {
        String input = "雄霸天下！";
        //创建密钥对
        String algorithm = "RSA";

        PrivateKey privateKey = getPrivateKey(algorithm, "b.pri");
        PublicKey publicKey = getPublicKey(algorithm, "a.pub");
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(input.getBytes());
        String encodeStr = Base64.encode(bytes);
        System.out.println("加密后:"+ encodeStr);

        //公钥解密
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        byte[] decode = Base64.decode(encodeStr);
        byte[] inputBytes = cipher.doFinal(decode);

        System.out.println("解密后："+new String(inputBytes));

    }


   // private static String encryptRAS(String algorithm,)

    /**
     * 保存公钥，密钥到根路径
     * @param algorithm
     * @param pubPath
     * @param priPath
     * @throws Exception
     */
    public static void generateKeyToFile(String algorithm,String pubPath,String priPath) throws Exception{
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey aPrivate = keyPair.getPrivate();
        PublicKey aPublic = keyPair.getPublic();
        //获取私钥字节数组
        byte[] privateEncoded = aPrivate.getEncoded();
        //获取公垚字节数组
        byte[] publicEncoded = aPublic.getEncoded();
        //使用base64进行编码
        String privateEncodeString = Base64.encode(privateEncoded);
        String publicEncodeString = Base64.encode(publicEncoded);

        FileUtils.writeStringToFile(new File(pubPath),publicEncodeString, Charset.forName("utf-8"));
        FileUtils.writeStringToFile(new File(priPath),privateEncodeString,Charset.forName("utf-8"));
    }

    /**
     * 获取私钥key
     * @param algorithm
     * @param priPath
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String algorithm,String priPath) throws Exception{
        String privateKeyString = FileUtils.readFileToString(new File(priPath), Charset.forName("utf-8"));
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        KeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 读取公钥key
     * @param alogrithm
     * @param pubPath
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String alogrithm,String pubPath) throws Exception{
        String publicKeyString = FileUtils.readFileToString(new File(pubPath), Charset.forName("utf-8"));
        KeyFactory keyFactory = KeyFactory.getInstance(alogrithm);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        return keyFactory.generatePublic(keySpec);
    }

}
