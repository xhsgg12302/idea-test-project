package me.maupassant.springboot.config;

import me.maupassant.springboot.utils.stackoverflow.Test;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-08
 * @Desc:
 */
@Configuration
public class LaodingOrderTestConfig {


    public LaodingOrderTestConfig(){
        System.out.println("++++++++++++++=");
    }

    //@Resource
    private Test test2;
}
