package me.maupassant.springmvc.entity;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-09-18
 * @Desc:
 */
public class TestObject {

    public static void  work(){

        synchronized (TestObject.class){

            while(true){
                System.out.println(Thread.currentThread().getName() + "   doing something...");

                try {
                    Thread.sleep(2 * 1000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
