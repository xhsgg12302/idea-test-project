package site.wtfu.framework.utils;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestExecutionListeners;

import java.util.*;

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


    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";

    // Declaring the color
    // Custom declaration
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
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

    public static void printHex(String color,byte[] bytes, int off, int len) {
        StringBuilder hexString = new StringBuilder(color);
        int count = 0;

        for (int i = off; i < len && i < bytes.length; i++) {
            // 将每个字节转换为两位的十六进制字符串并追加到 StringBuilder 中
            hexString.append(String.format("%02x ", bytes[i]));
            count++;

            // 每 16 个字节后换行
            if (count % 16 == 0) {
                hexString.append("\n");
            }
        }

        hexString.append(ANSI_RESET);
        System.out.println(hexString);
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
    @Test
    public void testUUID(){
        UUID uuid = UUID.fromString("5f968565-cfe2-49c5-b13f-e540f8e002dc0");
        try {
            byte[] uuidBytes = toBytes(uuid);
            System.out.println("Converted bytes: " + Arrays.toString(uuidBytes));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    public void testPrintByte(){
        byte[] target = new byte[]{0x03, 0x04, 0x05};
        printHex(ANSI_RED, target, 0, target.length);
    }

    @Test
    public void testLambda(){
        List<String> lists = new ArrayList<String>();
        lists.add("Bob");
        lists.add("Alice");
        lists.add("Casmtons");

        Collections.sort(lists, (o1, o2) -> o1.compareTo(o2));

        Comparator<String> stringComparator = String::compareTo;

        Collections.sort(lists, String::compareTo);


    }
}

