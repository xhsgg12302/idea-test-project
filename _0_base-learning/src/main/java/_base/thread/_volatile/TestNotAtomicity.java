package _base.thread._volatile;

import java.util.concurrent.CountDownLatch;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/3/3
 *                          @since  1.0
 *                          @author 12302
 * 
 */
class TestNotAtomicity {
    public static class InnerClass {
        public volatile int num = 0;
        public void numPlusPlus() { num++; }
    }

    public static void main(String[] args) throws InterruptedException {
        InnerClass object = new InnerClass();
        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    object.numPlusPlus();
                }
                System.out.println(Thread.currentThread().getName() + "has done");
                cdl.countDown();
            }).start();
        }
        cdl.await();
        System.out.println("current num value: " + object.num);
    }
}
