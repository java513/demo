package com.lh.bytebit;

import org.junit.Test;

/**
 * @program: encrypt-decrypt
 * @description: byte和bit的关系
 * @author: lh
 * @date: 2021-06-26 17:31
 **/
public class ByteBit {
    public static void main(String[] args) {
        String msg = "a";
        byte[] bytes = msg.getBytes();
        for (byte aByte : bytes) {
            int c = aByte;
            System.out.println(c);
            //byte字节对应的bit是
            String binaryString = Integer.toBinaryString(c);
            System.out.println(binaryString);
        }
    }

    @Test
    public void testByte() throws Exception{
        String a = "伤";
        byte[] bytes = a.getBytes();
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }
        System.out.println("--------------");
        byte[] gbks = a.getBytes("GBK");
        for (byte gbk : gbks) {
            System.out.println(gbk);
        }
    }
}
