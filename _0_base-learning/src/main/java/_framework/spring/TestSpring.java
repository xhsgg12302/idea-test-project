package _framework.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Author:12302
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/7/7 19:53
 * @Description:
 */
public class TestSpring {
    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
    }
}
