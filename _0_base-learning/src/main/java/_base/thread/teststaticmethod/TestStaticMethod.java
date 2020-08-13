package _base.thread.teststaticmethod;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-18
 * @Desc:
 */
public class TestStaticMethod {


    public static void main(String[] args) {
        CountDownLatch cdt = new CountDownLatch(1);

        new Thread(new Mythread(cdt),"1").start();
        new Thread(new Mythread(cdt),"2").start();

        cdt.countDown();
    }
}


class Mythread implements Runnable{
    private CountDownLatch countDownLatch;

    public Mythread(CountDownLatch countDownLatch){
        this.countDownLatch= countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true){
            Random random = new Random();
            int z = random.nextInt(10);
            SharpFile.setString(z);
        }
    }
}