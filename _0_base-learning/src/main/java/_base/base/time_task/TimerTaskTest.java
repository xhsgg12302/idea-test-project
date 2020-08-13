package _base.base.time_task;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-06
 * @Desc:
 */
public class TimerTaskTest {

    private static TimerTask timerTask = new TimerTask(){

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"\t正在连接...");
        }
    };

    public static void main(String[] args) {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask,2*1000L,5*1000L);

        try {
            Thread.sleep(20*1000L);
            timer.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
