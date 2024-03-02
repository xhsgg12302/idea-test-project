package _base.thread.test;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/2/29
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class StartMethod {

    public static void main(String[] args) {

        new PrimeThread().start();

        new Thread(new PrimeRun()).start();
    }

}

class PrimeThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": hello world");
    }
}

class PrimeRun implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": hello world");
    }
}
