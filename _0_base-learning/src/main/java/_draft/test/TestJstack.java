package _draft.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-05-26
 */
public class TestJstack {

    private static final Object locl = new Object();

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(10);

        executor.execute(new Runner());
        executor.execute(new Runner());

        keepRunning();
    }

    static class Runner implements Runnable{

        @Override
        public void run() {
            synchronized (locl){
                calculate();
            }
        }

        private void calculate(){
            long i = 0;
            while(true){
                i++;
            }
        }
    }


    public static void keepRunning(){
        Thread thread = Thread.currentThread();
        synchronized (thread){
            try {
                thread.wait();
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
