package site.wtfu.framework.config;

import site.wtfu.framework.service.ItestAop;
import site.wtfu.framework.service.ItestLifecycle;
import site.wtfu.framework.utils.stackoverflow.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

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
