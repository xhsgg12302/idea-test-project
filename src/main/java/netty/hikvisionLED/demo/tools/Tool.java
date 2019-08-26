package netty.hikvisionLED.demo.tools;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-21
 * @Desc:
 */
public class Tool {

    public static int bytesToInt(byte[] src, int offset) {
        int value;
        value = ((src[offset] & 0xFF)<<24)
                |((src[offset+1] & 0xFF)<<16)
                |((src[offset+2] & 0xFF)<<8)
                |(src[offset+3] & 0xFF);
        return value;
    }
    public static short bytesToShort(byte[] src, int offset) {
        short value;
        value = (short) (((src[offset] & 0xFF)<<8)
                |(src[offset+1] & 0xFF));
        return value;
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
}
