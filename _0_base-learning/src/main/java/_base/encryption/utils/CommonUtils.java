package _base.encryption.utils;

import org.junit.Test;

import java.util.Random;

public class CommonUtils {

    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    /**
     * 字节数据转十六进制字符串
     *
     * @param data 输入数据
     * @return 十六进制内容
     */
    public static String byteArrayToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            // fix: 不用先与在移位.
            // stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
            stringBuilder.append(HEX_CHAR[(data[i]) >>> 4]);
            // 取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i < data.length - 1) {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }

    @Test
    public void test_byteArrayToString(){
        String tmp = "hello world";
        String hex = byteArrayToString(tmp.getBytes());

        // 68 65 6C 6C 6F 20 77 6F 72 6C 64
        System.out.println(hex);
    }

    public static byte[] getRandomKey(){
        int len = 16; // 128 ->16  256->32
        byte[] keys = new byte[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            keys[i] = (byte) (random.nextInt(126-34) +34);
        }
        return keys;
    }
}
