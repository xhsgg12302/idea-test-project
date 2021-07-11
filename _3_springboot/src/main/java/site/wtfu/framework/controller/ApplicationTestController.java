package site.wtfu.framework.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright 2021 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2021-05-01
 */
@Controller
@RequestMapping(value = "application")
public class ApplicationTestController {


    @Value("${name}")
    public String name;

    @RequestMapping(value = "current")
    @ResponseBody
    public String getApplication(){
        return name;
    }

}
