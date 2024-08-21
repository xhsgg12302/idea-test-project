package _base.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/6/8 1:27
 * @Description:
 */
public class TestGBK {
    public static void main1(String[] args) throws Exception{
        aa();
    }


    public static void main(String[] args) {
        char a = 20320;
        char b = 0x4f60;
        char c = 65535;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        hex2decimal();
    }






    /**
     * copy from : java.util.Properties#loadConvert(char[], int, int, char[])
     */
    private static void hex2decimal(){
        int value = 0;
        char[] chars = new char[]{'4','f','6','0'};

        for (char aChar : chars) {
            switch (aChar) {
                case '0': case '1': case '2': case '3': case '4':
                case '5': case '6': case '7': case '8': case '9':
                    value = (value << 4) + aChar - '0';
                    break;
                case 'a': case 'b': case 'c':
                case 'd': case 'e': case 'f':
                    value = (value << 4) + 10 + aChar - 'a';
                    break;
                case 'A': case 'B': case 'C':
                case 'D': case 'E': case 'F':
                    value = (value << 4) + 10 + aChar - 'A';
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Malformed \\uxxxx encoding.");
            }
        }
        System.out.println(value);
    }

    public static void aa() throws Exception{
        String abc = "������ǧ��";

        System.out.println(abc);

        File file = new File("C:\\cameraConfig\\encode\\name_gbk.txt");
        if(!file.exists()){
            file.createNewFile();
        }

        for (byte b  : abc.getBytes("gbk")){
            System.out.println(Integer.toHexString(b & 0xff));
        }

        OutputStream os = new FileOutputStream(file);
        os.write(abc.getBytes("gbk"));
        os.flush();
        os.close();
    }
    
    
    public static void cc(){
        String temp = "2020年统计用区划代码和城乡划分代码";
        
        
        
    }
}

