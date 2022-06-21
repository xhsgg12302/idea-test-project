package _framework.spring.cycle_reference;

import _framework.spring.bean_lifecycle.SpringBeanLifeCycleTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-05-12
 */
public class SpringCycleReferenceTest {

    public static void main(String[] args) {

        // 获取xml文件的一种方式。
        URL config = SpringBeanLifeCycleTest.class.getResource("../config/cycle_reference.xml");

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("_framework/spring/config/cycle_reference.xml");

        Teacher teacher = (Teacher) applicationContext.getBean("tea");
        System.out.println(teacher);

        Student student = (Student) applicationContext.getBean("stu");
        System.out.println(student);

        applicationContext.close();
    }
}
