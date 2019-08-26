package classLoader.stack;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-17
 * @Desc:
 */
public class Bdemo {

    private Cdemo cdemo = new Cdemo();

    public Bdemo() {
        System.out.println("new B()");
    }

    public void getB(){
        cdemo.getC();
    }

    public static void getBStatic(){
        Cdemo.getCStatic();
    }
}
