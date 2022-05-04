package _base.thread._daemon;

import java.util.concurrent.TimeUnit;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-04-27
 */
public class Test {

    public static void testDemo() {
        Thread daemon = new Thread(() -> {
            try {
                int n = 10;
                while(n-- > 0) {
                    System.out.println("start daemon thread sleep 10s");
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        daemon.start();
    }

    public static void testDaemon() {
        Thread daemon = new Thread(() -> {
            while (true) {
                System.out.println("start daemon thread sleep 10s");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemon.setDaemon(true);
        daemon.start();
    }

    public static void main(String[] args) {
        //testDemo();
        testDaemon();
        System.out.println("main thread has done!!!");
    }
}
