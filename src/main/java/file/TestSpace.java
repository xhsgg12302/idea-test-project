package file;

import java.io.File;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/5/30 16:48
 * @Description:
 */
public class TestSpace {
    public static void main(String[] args) {
        File path = new File("C:\\Program Files\\Java\\jre7\\README.txt");
        if(!path.exists()) path = new File("");
        System.out.println("============根目录绝对路径  path.getAbsolutePath()="+path.getAbsolutePath());
        System.out.println("============根目录路径  path.getPath()="+path.getPath());
        String templatePath = path.getPath();  // 根目录路径
        System.out.println(templatePath);
    }
}
