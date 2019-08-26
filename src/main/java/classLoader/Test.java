package classLoader;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/18 14:45
 * @Description:
 */

class A {
    public static void prt(){
        System.out.println("123");
    }
    public A(){
        System.out.println("A");
    }
}
public class Test extends A{
    public static void prt(){
        System.out.println("456");
    }
    public Test(){
        super.prt();
        System.out.println("B");
    }

    public static void main(String[] args) {
        A a =new Test();
    }
}
