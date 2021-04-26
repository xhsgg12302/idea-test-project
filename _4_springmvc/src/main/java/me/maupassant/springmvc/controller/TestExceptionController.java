package me.maupassant.springmvc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "exception")
public class TestExceptionController {

    @RequestMapping(value = "query")
    @ResponseBody
    public String TestFirst(@RequestParam("uid") Long userId){
        return userId + "";
    }

    @RequestMapping(value = "calc")
    @ResponseBody
    public String calc(){
        int a = 2/ 0;
        return "";
    }


    ///////////////////////
    // 属于这个controller的异常处理
    ///////////////////////
    //@ExceptionHandler(Exception.class)
    @ResponseBody
    public String exception(Exception ex){
        return this.getClass().getSimpleName() + ": thisController : " + ex.getMessage();
    }

}
