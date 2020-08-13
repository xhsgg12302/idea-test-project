package _jvm.classLoader.stack;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-17
 * @Desc:
 */
public class TestStack {
    public static void main(String[] args) {
        /*Ademo ademo = new Ademo();
        ademo.getA();
        System.out.println("a invoke stop");*/
        Ademo.getAStatic();
        System.out.println("a invoke stop");
    }
}
