package io.byteArrayOutputDir;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Author:12302
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/7/6 15:57
 * @Description:
 */
public class TestbyteArrayOutput {
    public static void main(String[] args) throws Exception{
        ByteArrayOutputStream bOutput = new ByteArrayOutputStream(12);
        while( bOutput.size()!= 10 ) {
            // 获取用户输入

            int bytee = System.in.read();
            bOutput.write(bytee);
        }
        byte b [] = bOutput.toByteArray();
        System.out.println("Print the content");
        for(int x= 0 ; x < b.length; x++) {
            // 打印字符
            System.out.print((char)b[x]  + "   ");
        }
        System.out.println("   ");
        int c;
        ByteArrayInputStream bInput = new ByteArrayInputStream(b);
        System.out.println("Converting characters to Upper case " );
        for(int y = 0 ; y < 1; y++ ) {
            while(( c= bInput.read())!= -1) {
                System.out.println(Character.toUpperCase((char)c));
            }
            bInput.reset();
        }
    }
}
