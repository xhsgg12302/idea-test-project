package site.wtfu.framework.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Copyright 2021 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2021-06-14
 */
/* _12302_2022/4/20_< 取消定时 > */
//@Component
public class ScheduleTask {


    @Scheduled(cron = "0/1 * * * * ?")
    public void test(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + new Date()  + "\t 1");
    }

    @Scheduled(cron = "0/1 * * * * ?")
    public void test2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + new Date() + "\t 2" );
    }

}
