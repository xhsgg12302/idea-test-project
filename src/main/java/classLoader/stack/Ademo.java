package classLoader.stack;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-17
 * @Desc:
 */
public class Ademo {

    private Bdemo bdemo = new Bdemo();

    public Ademo() {
        System.out.println("new A()");
    }

    public void getA(){
        bdemo.getB();
    }

    public static void getAStatic(){
        Bdemo.getBStatic();
    }
}
