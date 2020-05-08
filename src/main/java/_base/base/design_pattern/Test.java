package _base.base.design_pattern;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-09-02
 * @Desc:
 */
public class Test {
    public static void main(String[] args) {


        method1();
        //method2();
    }
    public static void method1(){
        Integer threadNum = 20;

        CountDownLatch cdl = new CountDownLatch(1);

        for (int i = 0; i< threadNum;i ++){
            new Thread(()->{
                try {
                    cdl.await();
                    //System.out.println(System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //饿汉
                //int hash = Singleton1.getSingleton1().hashCode();
                //懒汉
                int hash = Singleton3.getInstance().hashCode();
                System.out.println(hash);
            }).start();
        }
        cdl.countDown();
    }
    public static void method2(){
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new TestThread();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
class TestThread extends Thread {
    @Override
    public void run() {
        //System.out.println(System.currentTimeMillis());
        // 对于不同单例模式的实现，只需更改相应的单例类名及其公有静态工厂方法名即可
        int hash = Singleton2.getSingleton2().hashCode();
        System.out.println(hash);
    }
}

