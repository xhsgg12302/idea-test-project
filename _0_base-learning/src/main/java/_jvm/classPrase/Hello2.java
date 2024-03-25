package _jvm.classPrase;

import _utils.thread.ThreadUtil;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/8
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class Hello2 {
    private int num;

    static {
        System.out.println("hello static block");
    }

    private static String ss = clinit();

    private static String other = "xhsgg12302@outlook.com";
    public static String clinit(){
        System.out.println("load clinit");
        String def = "xhsgg12302@gmail.com";
        return "xhsgg12302@wtfu.site";
    }
    public int foo() {
        String bac = "xhsgg12302@126.com";
        return num;
    }

    public static void main(String[] args) {
        String hij = "xhsgg12302@qq.com";
        ThreadUtil.keepRunning();
    }
}
