package _jvm.hsdb;

import _utils.thread.ThreadUtil;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/28
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class Sample {

    static String sample01 = new String("unique smaple01");

    public String sample02 = new String("unique sample02");

    private final static String fs_s1 = "this is unique String for final static String fs_s1";

    private static String s_s2 = "this is unique String for static String s_s2";

    private final String f_s2 = "this is unique String for final String f_s2";

    public static int i_int3 = 123021;

    public int i_int1 = 190;

    public int i_int2 = 123020;


    public void sampleMethod01(){}

    public String sampleMethod02(int int1, String abc){return abc;}


    public static void main(String[] args) {

        Sample sample03 = new Sample();

        ThreadUtil.keepRunning();
    }
}
