package _framework.spring.refresh_scope.common2;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/8/26
 *                          @since  1.0
 *                          @author 12302
 * 
 */
@Component(value = "84")
@ConfigurationProperties(prefix = "custom.prefix")
@PropertySource(
        name = "customProperties", // not systemProperties, systemEnvironment
        value = {"classpath:_framework/spring/res/db.properties"})
@Data
public class DbConfig2 {

    private String abc;

    private String def;

    private String hij;
}
