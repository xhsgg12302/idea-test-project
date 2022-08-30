package _base.io.bio;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/8/29
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class EndOfIns {
    public static void main(String[] args) {
        String abc = "hello world";
        String abc1 = "hello world \r";
        String abc11 = "hello world \r cc";
        String abc2 = "hello world \r\n";
        String abc22 = "hello world \r\n demo";

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(abc22.getBytes(StandardCharsets.UTF_8));

        int result;
        for (int i = 0; i < 20; i++) {
            result = byteArrayInputStream.read();
            if(result != -1){
                System.out.println((char)result);
            }else{
                System.out.println(result);
            }
        }
    }
}
