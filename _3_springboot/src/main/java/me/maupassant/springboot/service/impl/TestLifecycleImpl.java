package me.maupassant.springboot.service.impl;

import me.maupassant.springboot.service.ItestLifecycle;
import org.springframework.context.Lifecycle;
import org.springframework.stereotype.Component;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-24
 * @Desc:
 */
@Component
public class TestLifecycleImpl implements ItestLifecycle, Lifecycle {

    public TestLifecycleImpl(){
        System.out.println("++++++++++++++++++");
    }

    private Thread curr;

    @Override
    public void start() {
        System.out.println("start()");
        curr = new Thread(()-> System.out.println(curr.getName() + "hello cycle"));
        curr.start();
    }

    @Override
    public void stop() {
        System.out.println("stop()");
        if(curr.isAlive()) System.out.println(curr.getName() + "_base.thread has death");
    }

    @Override
    public boolean isRunning() {
        return curr.isAlive();
    }
}
