package _jvm.constant_pool;

import _utils.thread.ThreadUtil;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/12
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class ConstantTest {
    private static String invokeStaticMethod = clinit();

    private static String str1 = "example@outlook.com";

    private static Integer int1 = 12306;
    public static String clinit(){
        String str2 = "example@gmail.com";
        Integer int1 = 12302;
        Integer int3 = new Integer(12303);
        int int2 = 123021;
        return "example@wtfu.site";
    }
    public int foo() {
        String str4 = "example@126.com";
        return 255;
    }
















    /**
     * 不是所有的字面量都会在刚加载完类后就会在运行时常量池解析。比如str4就没有对应的数据在堆里面。
     * @param args
     */
    public static void main(String[] args) {
        Integer int3 = new Integer(12304);
        String str5 = "example@qq.com";
        ThreadUtil.keepRunning();
    }
}
