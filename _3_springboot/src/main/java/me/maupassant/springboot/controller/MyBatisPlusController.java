package me.maupassant.springboot.controller;

import me.maupassant.springboot.service.IEmployeesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Copyright 2021 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2021-05-05
 */
@RestController
@RequestMapping(value = "mp")
public class MyBatisPlusController {

    @Resource
    public IEmployeesService employeesService;

    @RequestMapping(value = "test1")
    public Object test1(){

        return employeesService.list();
    }

}
