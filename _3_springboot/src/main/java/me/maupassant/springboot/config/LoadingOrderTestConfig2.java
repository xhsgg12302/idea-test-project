package me.maupassant.springboot.config;

import me.maupassant.springboot.utils.stackoverflow.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-08
 * @Desc:
 */
@Configuration
//@Order(1000)
public class LoadingOrderTestConfig2 implements Ordered {

    public LoadingOrderTestConfig2(){
        System.out.println("++++++++++++");
    }

    @Resource
    private Test test2;

    @Override
    public int getOrder() {
        return 100000;
    }
}
