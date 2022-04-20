package site.wtfu.framework.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import site.wtfu.framework.config.BeanMyScope;
import site.wtfu.framework.utils.SpringContextUtils;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-21
 * @Desc:
 */
@Component
public class InitRuner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        //Test.main(null);
        ApplicationContext applicationContext = SpringContextUtils.applicationContext;

        if(applicationContext instanceof AnnotationConfigEmbeddedWebApplicationContext){
            AnnotationConfigEmbeddedWebApplicationContext cast = AnnotationConfigEmbeddedWebApplicationContext.class.cast(applicationContext);
            cast.getBeanFactory().registerScope(BeanMyScope.SCOPE_MY, new BeanMyScope());
        }
    }
}
