package _base.inner_class;

/**
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @author 12302
 * @date 2021/9/2
 * @since
 */
public class TestLocalClass {

    private static int num1 = 1;

    public static int num2 = 2;

    public int num3 = 3;

    private int num4 = 4;

    public void test(){
        int num5 = 5;
        class LocalInner{
            public void test(){
                System.out.println("Local:" + (num1 + num2 + num3 + num4 + num5));
            }
        }
        new LocalInner().test();
    }

    public static void main(String[] args) {
        new TestLocalClass().test();
    }
}
