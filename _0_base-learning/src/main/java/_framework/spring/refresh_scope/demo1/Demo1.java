package _framework.spring.refresh_scope.demo1;

import _framework.spring.refresh_scope.common.DbConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/8/24
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Demo1 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("_framework.spring.refresh_scope");
        //context.register(ConfigSource.class);
        //context.refresh();

        DbConfig dbConfig = context.getBean(DbConfig.class);
        System.out.println(dbConfig);
    }
}
