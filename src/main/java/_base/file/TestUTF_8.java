package _base.file;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/6/8 1:14
 * @Description:
 */
public class TestUTF_8 {
    public static void main(String[] args) {

        int i = 1;
        int j = 4;
        System.out.println(i & j);
        /*try {
            File _base.file = new File("C:/users/elizabeth/desktop/8.jpg");
            FileInputStream is = new FileInputStream(_base.file);
            byte [] a = new byte[1024];
            is.read(a);
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        System.out.println(genrate());
    }
    public static String genrate(){
        String pwd = "";
        Random rand = new Random();
        int d = 0,x = 0,n = 0,min = 1,max = 3;
        for(int i = 0;i < 9;){
            int command = rand.nextInt(max - min + 1) + min;
            if(command == 1 && d <4) {
                pwd += (char) getDa(); d++; i++;
            }
            if(command == 2 && x <3){
                pwd += (char)getXi(); x++; i++;
            }
            if(command == 3 && n < 2){
                pwd += (char) getNum(); n++; i++;
            }
        }
        return pwd;
    }

    public static int getDa(){
        int MAX = 90;
        int MIN = 65;
        Random rand = new Random();
        return rand.nextInt(MAX - MIN + 1) + MIN;
    }
    public static int getXi(){
        int MAX = 122;
        int MIN = 97;
        Random rand = new Random();
        return rand.nextInt(MAX - MIN + 1) + MIN;
    }
    public static int getNum(){
        int MAX = 57;
        int MIN = 48;
        Random rand = new Random();
        return rand.nextInt(MAX - MIN + 1) + MIN;
    }






































    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String grade = sc.nextLine();
        String [] a = grade.split(",");
        for(int i =0; i < a.length ;i++){
            int tep = Integer.parseInt(a[i]);

            System.out.print(transf1(tep));
        }
        System.out.println();
    }

    public static String transf1(int grade){
        String temp = "";
        int zz = (grade-4)/10;
        switch (zz) {
            case 8:
                temp =  "A";break;
            case 7:
                temp =  "B";break;
            case 6:
                temp =  "C";break;
            default:
                temp =  "D";break;
        }
        return temp;
    }



































































    public static void main1(String[] args) throws Exception{
        String abc = "你好";

        String acd = new String(abc.getBytes("utf-8"),"gbk");

        System.out.println(acd);



        /*for (byte b  : abc.getBytes("utf-8")){
            System.out.println(Integer.toHexString(b & 0xff));
        }
        System.out.println("==============");
        for (byte b  : abc.getBytes("GBK")){
            System.out.println(Integer.toHexString(b & 0xff));
        }*/

    }
















    /**
     * 写入txt文件
     *
     * @param result
     * @param fileName
     * @return
     */
    public static boolean writeDataHubData(List<String> result, String fileName) {
        long start = System.currentTimeMillis();
        String filePath = "D:\\export\\txt";
        StringBuffer content = new StringBuffer();
        boolean flag = false;
        BufferedWriter out = null;
        try {
            if (result != null && !result.isEmpty() && StringUtils.isNotEmpty(fileName)) {
                fileName += "_" + System.currentTimeMillis() + ".txt";
                File pathFile = new File(filePath);
                if(!pathFile.exists()){
                    pathFile.mkdirs();
                }
                String relFilePath = filePath + File.separator + fileName;
                File file = new File(relFilePath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));
//                //标题头
//                out.write("curr_time,link_id,travel_time,speed,reliabilitycode,link_len,adcode,time_stamp,state,public_rec_time,ds");
//                out.newLine();
                for (String info : result) {

                    out.write(info);
                    out.newLine();
                }
                flag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return flag;
        }
    }
}
