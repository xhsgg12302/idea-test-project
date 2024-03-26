package site.wtfu.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.wtfu.framework.entity.User;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/26
 *                          @since  1.0
 *                          @author 12302
 *
 */
@Controller
@RequestMapping(value = "/msg/convert")
public class TestMessageConverterController {

    @RequestMapping(value = "index")
    public String index(){
        return "messageConvert";
    }

    @RequestMapping(value = "/ldt")
    @ResponseBody
    public User testLocalDateTime(@RequestBody User user){
        return user;
    }
}
