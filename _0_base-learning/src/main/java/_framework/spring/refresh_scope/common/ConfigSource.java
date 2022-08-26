package _framework.spring.refresh_scope.common;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/8/24
 *                          @since  1.0
 *                          @author 12302
 * 
 */
//@Configurable

@Component
//@ComponentScan 可以给applicationContext传参数解决
//@PropertySource({"file:../res/db.properties"})
//@PropertySource({"file:${../res/db.properties}"})
@PropertySource(
        name = "customProperties", // not systemProperties, systemEnvironment
        value = {"classpath:_framework/spring/res/db.properties"})
public class ConfigSource {
}
