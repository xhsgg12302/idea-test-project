package _base.base.thread_lock;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-09-18
 * @Desc:
 */
public class Entity {
    public static synchronized void common(){
        while(true){
            System.out.println(Thread.currentThread().getName() + "doing something..., please hold on");
            try {
                Thread.sleep(2 * 1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
