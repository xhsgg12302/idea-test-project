package base.time_task;


import java.util.concurrent.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-06
 * @Desc:
 */
public class SchedulePoolTask {

    private static Thread taskThread = new Thread(()->{
        System.out.println(Thread.currentThread().getName()+"\t正在连接...");
    },"test");



    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(taskThread,2,5, TimeUnit.SECONDS);

        try {
            Thread.sleep(20*1000L);
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
