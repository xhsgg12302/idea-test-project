package site.wtfu.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.wtfu.framework.entity.Employee;

import java.util.Date;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/22
 *                          @since  1.0
 *                          @author 12302
 *
 */
@Controller
@RequestMapping(value = "/args")
public class TestResolveArgumentController {

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
}
