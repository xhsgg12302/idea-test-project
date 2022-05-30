package _jvm.classPrase;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-05-25
 *
 * calss 文件常量池存放以下东西：
 *
 *      字面量：
 *          文本字符串
 *          final 修饰的基本类型没有符号引用
 *          被声明为final的常量
 *          基本数据类型的值
 *
 *      符号引用：
 *          类的完全限定名
 *          字段的名称和描述符
 *          方法的名称和描述符
 *
 *
 */
public class Hello {

    private int test;

    private boolean success;

    private String name = "xhsgg12302";

    private       int age = 18;

    private final int iAge =  20;

    private final float ratio = 0.75f;

    private final String const_var = "CONST";

    private final static String const_static_var = "STATIC_CONST";

    public int test(){
        return test;
    }

    public void demo(){}

    public void strTest(){

        // slot 复用演示
        {
            String s1 = "HELLO";

            // 最后一行定义的局部变量看不到，因为最后一行定义的变量属于浪费，估计是JVM优化相关。
            int b = 0;
            int c = 1;
        }
        String s2 = "HELLO";
        String str1 = new String("HELLO");
        String str2 = new String("HE") + new String("LLO");
    }

    private void foo() {
        {
            int a = 1;
            int b = 2;
        }
        {
            float c = 3f;
        }
        {
            long d = 4;
        }
    }

    public static void main(String[] args) {

        int b = 0;
    }
}
