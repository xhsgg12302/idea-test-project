package me.maupassant.springboot.config;

import me.maupassant.springboot.service.ItestAop;
import me.maupassant.springboot.service.ItestLifecycle;
import me.maupassant.springboot.utils.stackoverflow.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-08
 * @Desc:
 */
@Configuration
//@Order(12302)
public class LoadingOrderTestConfig implements Ordered {

    public LoadingOrderTestConfig(){
        System.out.println("++++++++++++");
    }

    @Bean
    public String test(){
        return new String("123");
    }

    @Bean
    public Test test1(){
        return new Test();
    }

    @Resource
    private Test test2;


    @Resource
    private ItestAop itestAop;

    @Resource
    private ItestLifecycle itestLifecycle;


    @Override
    public int getOrder() {
        return 12302;
    }
}
