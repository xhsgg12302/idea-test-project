package _base.base.design_pattern;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-09-02
 * @Desc:
 */
public class Singleton3 {

    private static volatile Singleton3 singleton3 = null;

    private Singleton3(){}

    public static Singleton3 getInstance(){
        if(null == singleton3){
            synchronized (Singleton3.class){
                if(null == singleton3){
                    singleton3 = new Singleton3();
                }
            }
        }
        return singleton3;
    }
}
