package _base.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/6/15 17:35
 * @Description:
 */
public class CountDownLatchTest {

    /**
     * _12302_ 此现象是前半部分m1,m2一起执行，m1完事儿，m2继续（此时countdownlatch不起作用了），m3也开始执行
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyThrd m1 = new MyThrd(countDownLatch,5);
        MyThrd m2 = new MyThrd(countDownLatch,8);
        MyThrd m3 = new MyThrd(countDownLatch,3);
        new Thread(m1).start();
        new Thread(m2).start();
        countDownLatch.await();
        new Thread(m3).start();


        System.out.println("main has dead");
    }
}

class MyThrd implements Runnable{
    private CountDownLatch countDownLatch;
    private Integer count;

    public MyThrd(CountDownLatch countDownLatch,Integer count){
        this.countDownLatch = countDownLatch;
        this.count = count;
    }
    @Override
    public void run() {
        for(int i =0 ;i < count ; i++){
            System.out.println(Thread.currentThread().getName() + "\t" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        countDownLatch.countDown();
    }
}
