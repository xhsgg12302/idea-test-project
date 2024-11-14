package _base.queue;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/9/13
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class TestSynchronousQueue {

    @Test
    public void test01() {

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        // 启动一个消费者
        new Thread(() -> {
            String poll = null;
            try {
                poll = queue.take();
            } catch (InterruptedException e) { throw new RuntimeException(e);}
            System.out.println("consumer take a item :" + poll);
        }).start();

        // 启动十个生产者
        for (int i = 0; i < 10; i++) {
            final int it = i;
            new Thread(() -> {
                boolean offer = queue.offer(it + "");
                System.out.println("thread name " + Thread.currentThread().getName() + " has commit:" + offer);
            }).start();
        }
    }

    @Test
    public void test02(){

        SynchronousQueue<String> queue = new SynchronousQueue<>();
        // 启动十个生产者
        for (int i = 0; i < 10; i++) {
            final int it = i;
            new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println("thread name " + name + " enter!");
                try {
                    queue.put(it + "");
                } catch (InterruptedException e) { throw new RuntimeException(e); }
                System.out.println("thread name " + name + " has commit:");
            }).start();
        }


        // 启动一个消费者
        new Thread(() -> {
            String poll = null;
            try {
                poll = queue.take();
            } catch (InterruptedException e) { throw new RuntimeException(e); }
            System.out.println("consumer take a item :" + poll);
        }).start();
    }
}
