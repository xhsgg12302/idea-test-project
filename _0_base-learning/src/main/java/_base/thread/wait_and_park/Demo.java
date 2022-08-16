package _base.thread.wait_and_park;

import _utils.thread.ThreadUtil;

import java.util.concurrent.locks.LockSupport;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/7/24
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Demo {
    private Object mutex = new Object();

    public void waitObject(){
        synchronized (mutex){
            try {
                mutex.wait();
            } catch (InterruptedException e) {e.printStackTrace();}
        }
    }

    private Object pObject = new Object();
    public void parkObject(){
        LockSupport.park(mutex);
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(demo::waitObject).start();
        new Thread(demo::parkObject).start();
        ThreadUtil.keepRunning();
    }
}
