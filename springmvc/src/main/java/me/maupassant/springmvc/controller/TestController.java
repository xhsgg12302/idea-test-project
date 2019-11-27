package me.maupassant.springmvc.controller;

import me.maupassant.springmvc.entity.Employee;
import me.maupassant.springmvc.entity.TestObject;
import me.maupassant.springmvc.entity.User;
import me.maupassant.springmvc.services.ITest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-14
 * @Desc:
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping("/testRb")
    @ResponseBody
    public Employee testRb(@RequestBody Employee e) {
        return e;
    }

    @RequestMapping("/testCustomObj")
    @ResponseBody
    public Employee testCustomObj(Employee e) {
        return e;
    }

    @RequestMapping("/testCustomObjWithRp")
    @ResponseBody
    public Employee testCustomObjWithRp(@RequestParam Employee e) {
        return e;
    }

    @RequestMapping("/testDate")
    @ResponseBody
    public Date testDate(Date date) {
        return date;
    }
    @RequestMapping("/sync")
    @ResponseBody
    public Object testSynchronized(){

        TestObject.work();
        return null;
    }

    @RequestMapping(value = "/ldt")
    @ResponseBody
    public User testLocalDateTime(@RequestBody User user){
        return user;
    }

    @Resource
    private ITest iTest;
    @RequestMapping(value = "/transaction")
    @ResponseBody
    public Object testTransaction(){
        User user = new User("909","world","hello");
        Integer result = iTest.testTransaction(user);
        return result;
    }
}
