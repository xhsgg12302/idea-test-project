package groupId;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeChatImgRevert {
    public static void main1(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(10);

        Runnable r = () -> {
            String tempDir = "/Users/stevenobelia/anft-backup/elizabeth-dsk/documents/WechatFiles/xhs12302/FileStorage/Image/2019-10";
            String[] fileName = getFileName(tempDir);
            int i = 0;
            for (String name : fileName) {

                try (InputStream reader = new FileInputStream(tempDir + "/" + name)) {
                    try (OutputStream writer = new FileOutputStream(tempDir + "/conversion/" + name + ".jpg")) {
                        byte[] bytes = new byte[1024];
                        int b;
                        while ((b = reader.read(bytes)) != -1) {//这里的in.read(bytes);就是把输入流中的东西，写入到内存中（buffer）。
//                        System.out.println("b = " + b + " b ^ 241 = " + (b ^ 241));
                            writer.write(b ^ 241);//241这个值是现算的，每个人电脑的值都不一致
                            writer.flush();
                        }
                    }
                    System.out.println(i++);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        pool.submit(r);
        pool.shutdown();
    }

    public static void main(String[] args) {
        String tempDir = "/Users/stevenobelia/anft-backup/elizabeth-dsk/documents/WechatFiles/xhs12302/FileStorage/Image/2019-09";
        File file = new File(tempDir);
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(getXor(file1));
        }
    }

    private static Object[] getXor(File file) {
        Object[] xor = null;
        if (file != null) {
            byte[] bytes = new byte[4];
            try (InputStream reader = new FileInputStream(file)) {
                reader.read(bytes, 0, bytes.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            xor = getXor(bytes);
        }
        return xor;
    }

    public static Map<String,String> FILE_TYPE_MAP = new HashMap(){{put("ffd8ffe000104a464946", "jpg");}};
    /**
     * @param bytes
     * @return
     */
    private static Object[] getXor(byte[] bytes) {
        Object[] xorType = new Object[2];
        int[] xors = new int[3];
        for (Map.Entry<String, String> type : FILE_TYPE_MAP.entrySet()) {
            String[] hex = {
                    String.valueOf(type.getKey().charAt(0)) + type.getKey().charAt(1),
                    String.valueOf(type.getKey().charAt(2)) + type.getKey().charAt(3),
                    String.valueOf(type.getKey().charAt(4)) + type.getKey().charAt(5)
            };
            xors[0] = bytes[0] & 0xFF ^ Integer.parseInt(hex[0], 16);
            xors[1] = bytes[1] & 0xFF ^ Integer.parseInt(hex[1], 16);
            xors[2] = bytes[2] & 0xFF ^ Integer.parseInt(hex[2], 16);
            if (xors[0] == xors[1] && xors[1] == xors[2]) {
                xorType[0] = type.getValue();
                xorType[1] = xors[0];
                break;
            }
        }
        return xorType;
    }



        public static String[] getFileName(String path) {

            File file = new File(path);

            String[] fileName = file.list();

            return fileName;

        }


}


