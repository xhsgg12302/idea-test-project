package _base.base.thread_lock;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-09-18
 * @Desc:
 */
public class Test {

    public static void main(String[] args) {
        new Thread(()->{
            Entity.common();
        }).start();
        new Thread(()->{
            Entity.common();
        }).start();
        new Thread(()->{
            Entity.common();
        }).start();
    }
}
