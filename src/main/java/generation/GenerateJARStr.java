package generation;


import java.nio.charset.Charset;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/4/10 15:19
 * @Description:
 */
public class GenerateJARStr {

    static final String fileDic = "";

    public static void genenaration(String fileDic) {

    }

    public static void main(String[] args) throws Exception {
        //String abc = "华夏五千年";
        String abc = "主进场请求";
        String csn = Charset.defaultCharset().name();
        //getBytes不加编码的时候，以系统默认编码进行加码，String csn = Charset.defaultCharset().name();
        String abc1 = new String(abc.getBytes("UTF-8"));
        String abc2 = new String(abc.getBytes(),"UTF-8");
        String abc3 = new String(abc.getBytes("GBK"));
        String abc4 = new String(abc.getBytes(),"GBK");
        String abc5 = new String(abc.getBytes("GBK"),"UTF-8");

        String utf2GbkString = new String(abc.getBytes("UTF-8"), "GBK");
        System.out.println("UTF-8转换成GBK：" + utf2GbkString);
        String utf2Gbk2UtfString = new String(utf2GbkString.getBytes("GBK"), "UTF-8");
        System.out.println("UTF-8转换成GBK再转成UTF-8：" + utf2Gbk2UtfString);

        //System.out.println(Byte.SIZE, Integer.SIZE);
    }

    public static String getEncoding(String str){
        String encode;

        encode = "UTF-16";
        try {
            if (str.equals(new String(str.getBytes(), encode))){
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "ASCII";
        try {
            if (str.equals(new String(str.getBytes(), encode))){
                return "字符串<< " + str + " >>中仅由数字和英文字母组成，无法识别其编码格式";
            }
        } catch (Exception ex) {
        }

        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(), encode))) {
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(), encode))){
                return encode;
            }
        } catch (Exception ex) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(), encode))){
                return encode;
            }
        } catch (Exception ex) {
        }

        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(), encode))){
                return encode;
            }
        } catch (Exception ex) {
        }
        return "未识别编码格式";
    }
}
