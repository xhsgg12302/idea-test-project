package io.test;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-03
 * @Desc:
 */
public class TestFixedSchedule {

    public static void main(String[] args) {

        /*try {
            Thread.sleep(3 * 1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        final ScheduledExecutorService schedule = Executors.newScheduledThreadPool(4);

        ScheduledFuture<?> future = schedule.scheduleAtFixedRate(() -> {
            String threadName = Thread.currentThread().getName();
            Random random = new Random();
            System.out.println(threadName + " : start...");
            try {
                Thread.sleep(2 * 1000l);
                int base = random.nextInt(2);
                System.out.println(base);
                int i =10/base;

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception ex){
                ex.printStackTrace();
            }
            System.out.println(threadName + " : stop...");
        }, 1, 1, TimeUnit.SECONDS);


    }
}
