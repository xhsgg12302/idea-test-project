package _base.base.big_and_little_endian;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-08
 * @Desc: int 占4B, 位数32
 */
public class Test {
    //将整数按照小端存放，低字节出访低位
    public static byte[] toLH(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    /**
     * 将int转为大端，低字节存储高位
     *
     * @param n
     * int
     * @return byte[]
     */
    public static byte[] toHH(int n) {
        byte[] b = new byte[4];
        b[3] = (byte) (n & 0xff);
        b[2] = (byte) (n >> 8 & 0xff);
        b[1] = (byte) (n >> 16 & 0xff);
        b[0] = (byte) (n >> 24 & 0xff);
        return b;
    }

     public static void demo(){
         ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
         // 存入字符串
         bb.asCharBuffer().put("abdcef");
         System.out.println(Arrays.toString(bb.array()));

         // 反转缓冲区
         bb.rewind();
         // 设置字节存储次序
         bb.order(ByteOrder.BIG_ENDIAN);
         bb.asCharBuffer().put("abcdef");
         System.out.println(Arrays.toString(bb.array()));

         // 反转缓冲区
         bb.rewind();
         // 设置字节存储次序
         bb.order(ByteOrder.LITTLE_ENDIAN);
         bb.asCharBuffer().put("abcdef");
         System.out.println(Arrays.toString(bb.array()));
     }

     public static String supplement(String origin,int bit){
        String temp = origin;
        int length = origin.length();
        if(length < bit){
            String zeros = "";
            for(int i = 0; i< bit - length ; i ++ ){
                zeros += "0";
            }
            temp = zeros + origin;
        }
        return temp;
     }


    public static void main(String[] args) {

        /*for (byte arg : toLH(123456789)) {
            System.out.println(Integer.toHexString(arg));
            //System.out.print(Integer.toHexString(arg & 0xff)+" ");
        }
        System.out.println("");
        *//*for (byte arg : toLH(123456789)) {
            System.out.print(Integer.toHexString(arg & 0xff)+" ");
        }
        System.out.println();*//*
        //demo();

        //System.out.println(Integer.toBinaryString(123456789));
        System.out.println(supplement(Integer.toBinaryString(123456789  &  0xFF),8));
        System.out.println(supplement(Integer.toBinaryString(123456789 >> 8 &  0xFF),8));
        System.out.println(supplement(Integer.toBinaryString(123456789 >> 16 &  0xFF),8));
        System.out.println(supplement(Integer.toBinaryString(123456789 >> 24 &  0xFF),8));

        System.out.println(Integer.toHexString(123456789));*/

        int i = 1437204480;
        System.out.println(Integer.toHexString(i));
        System.out.println(Integer.toHexString(i & 0xFFFF));
    }

}
