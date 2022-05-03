package site.wtfu.framework.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.wtfu.framework.compose.BusinessCompose;
import site.wtfu.framework.config.repeat.NoRepeatSubmit;

import javax.annotation.Resource;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-04-22
 */
@Controller
@RequestMapping(value = "bus")
public class TestBusinessController {

    @Resource
    private BusinessCompose businessCompose;

    @GetMapping("connectionLocation")
    @ResponseBody
    @NoRepeatSubmit(lockTime = 5)
    public Object connectionLocationTest(){
        Object o = businessCompose.queryEmp();
        return new JSONObject().fluentPut("code",0).fluentPut("desc","success");
    }

    @GetMapping("transactionLocation")
    @ResponseBody
    public Object transactionLocationTest(){
        Object o = businessCompose.updateSerial();
        return new JSONObject().fluentPut("code",0).fluentPut("desc","success");
    }
}
