package site.wtfu.framework.utils;

import java.util.Arrays;
import java.util.UUID;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/5
 *                          @since  1.0
 *                          @author 12302
 *
 */

public class HexUtil {

    /**
     * 将十六进制字符串转换为字节数组。
     *
     * @param hexStr 十六进制字符串，每个字节用两位表示，例如 "1A2B3C"。
     * @return 转换后的字节数组。
     * @throws IllegalArgumentException 如果输入的字符串不是有效的十六进制字符串。
     */
    public static byte[] hexStringToByteArray(String hexStr) {
        if (hexStr == null || hexStr.length() % 2 != 0) {
            throw new IllegalArgumentException("Invalid hexadecimal string length");
        }

        byte[] bytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length(); i += 2) {
            String hexPair = hexStr.substring(i, i + 2);
            bytes[i / 2] = (byte) Integer.parseInt(hexPair, 16);
        }
        return bytes;
    }

    public static byte[] hexToByteArray(String inHex){
        int hexlen = inHex.length();
        byte[] result;
        if (hexlen % 2 == 1){
            //奇数
            hexlen++;
            result = new byte[(hexlen/2)];
            inHex="0"+inHex;
        }else {
            //偶数
            result = new byte[(hexlen/2)];
        }
        int j=0;
        for (int i = 0; i < hexlen; i+=2){
            result[j]= hexToByte (inHex.substring(i,i+2));
            j++;
        }
        return result;
    }
    public static byte hexToByte(String inHex){
        return (byte)Integer.parseInt(inHex,16);
    }

    // 将UUID转换为字节数组
    public static byte[] toBytes(UUID uuid) {
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();
        byte[] bytes = new byte[16];

        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) (mostSigBits >> 8 * (7 - i));
        }
        for (int i = 8; i < 16; i++) {
            bytes[i] = (byte) (leastSigBits >> 8 * (7 - i));
        }

        return bytes;
    }

    // 测试方法
    public static void main(String[] args) {
        UUID uuid = UUID.fromString("5f968565-cfe2-49c5-b13f-e540f8e002dc0");
        try {
            byte[] uuidBytes = toBytes(uuid);
            System.out.println("Converted bytes: " + Arrays.toString(uuidBytes));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}

