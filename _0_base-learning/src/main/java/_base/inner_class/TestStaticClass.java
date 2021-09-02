package _base.inner_class;

/**
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2021/9/2
 */
public class TestStaticClass {

    private static int num1 = 1;

    public static int num2 = 2;

    public int num3 = 3;

    private int num4 = 4;

    public static class StaticInner{
        public void test(){
            System.out.println("access"  + num1 + num2);
        }
    }

    public static void main(String[] args) {
        StaticInner inner = new StaticInner();
        inner.test();
    }
}
