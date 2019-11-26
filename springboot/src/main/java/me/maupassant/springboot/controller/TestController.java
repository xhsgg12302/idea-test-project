package me.maupassant.springboot.controller;

import me.maupassant.springboot.service.ItestAop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-26
 * @Desc:
 */
@Controller
@RequestMapping(value = "test")
public class TestController {

    @RequestMapping(value = "test")
    @ResponseBody
    public Object test(){
        return new HashMap(){{put("code",1000);put("desc","SUCCESS");}};
    }


    @Resource
    private ItestAop itestAop;
    @RequestMapping(value = "aop")
    @ResponseBody
    public Object aop(){
        Object test = itestAop.test();
        return new HashMap(){{put("code",1000);put("desc",test);}};
    }

}
