package site.wtfu.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import site.wtfu.framework.entity.Dept;
import site.wtfu.framework.entity.Employee;
import site.wtfu.framework.web.anno.FormObj;

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


    /**
     * curl:
     *      http://localhost:8080/args/anno1?dept.name=abc&employee.id=12302&employee.name=xhsgg
     *      http://localhost:8080/args/anno2?d.name=abc&e.id=12302&e.name=xhsgg
     *      http://localhost:8080/args/anno3?d.name=abc&e.id=12302&e.name=xhsgg
     *
     * @param dept
     * @param emp
     * @return
     */
    @RequestMapping("/anno1")
    public String anno1(@FormObj Dept dept, @FormObj Employee emp) {
        return "args";
    }

    @RequestMapping("/anno2")
    public String anno2(@FormObj("d") Dept dept, @FormObj("e") Employee emp) {
        return "args";
    }

    @RequestMapping("/anno3")
    public String anno3(@FormObj(value = "d", show = false) Dept dept, @FormObj("e") Employee emp) {
        return "args";
    }
}
