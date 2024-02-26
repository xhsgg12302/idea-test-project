package _framework.spring.refresh_scope.demo2;

import _framework.spring.refresh_scope.common2.DbConfig2;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
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
public class Demo2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //context.register(ConfigSource.class);
        //context.refresh();

        context.register(ConfigurationPropertiesBindingPostProcessor.class);
        context.scan("_framework.spring.refresh_scope");
        context.refresh();

        DbConfig2 dbConfig = context.getBean(DbConfig2.class);
        System.out.println(dbConfig);
    }
}
