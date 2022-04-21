package site.wtfu.framework.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.wtfu.framework.beans.User;
import site.wtfu.framework.utils.SpringContextUtils;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-04-20
 */
@RestController
@RequestMapping("scope")
public class TestScopeController {

    @RequestMapping("test1")
    public Object test1(){

        System.out.println("从容器中获取User对象");
        User user = SpringContextUtils.getBean(User.class);
        System.out.println("user对象的class为：" + user.getClass());

        System.out.println("多次调用user的getUsername感受一下效果\n");
        for (int i = 1; i <= 3; i++) {
            System.out.println(String.format("********\n第%d次开始调用getUsername", i));
            System.out.println(user.getUsername());
            System.out.println(String.format("第%d次调用getUsername结束\n********\n", i));
        }

        return null;
    }
}
