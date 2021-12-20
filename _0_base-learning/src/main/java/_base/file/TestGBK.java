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
    public static void main(String[] args) throws Exception{
        aa();
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

