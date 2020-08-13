package _jvm.classLoader.stack;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-17
 * @Desc:
 */
public class Cdemo {

    private Ddemo ddemo = new Ddemo();

    public Cdemo() {
        System.out.println("new C()");
    }

    public void getC(){
        ddemo.getD();
    }

    public static void getCStatic(){
        Ddemo.getDStatic();
    }
}
