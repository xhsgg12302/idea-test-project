package me.maupassant.springboot.service.impl;

import me.maupassant.springboot.service.ItestAop;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-26
 * @Desc:
 */
@Service
public class TestAopImpl implements ItestAop {

    @Async
    @Override
    public Object test() {

        System.out.println("测试方法执行");
        try {
            Thread.sleep(10*1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试方法执行结束");
        return "SUCCESS";
    }
}
