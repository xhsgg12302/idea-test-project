package _jvm.classLoader.stack;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-17
 * @Desc:
 */
public class Ddemo {

    private Edemo edemo = new Edemo();

    public Ddemo() {
        System.out.println("new D()");
    }

    public void getD(){
        edemo.getE();
    }

    public static void getDStatic(){
        Edemo.getEStatic();
    }
}
