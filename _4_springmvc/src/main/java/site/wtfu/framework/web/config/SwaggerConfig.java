/*
package site.wtfu.framework.web.config;

import com.google.common.base.Predicates;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProviderExposingInterceptor;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;

*/
/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/7/19
 *                          @since  1.0
 *                          @author 12302
 * 
 *//*

@Configuration
@EnableSwagger2
*/
/**
 * 1,添加了静态资源，这个注解有坑，在springboot 下会是mvc自动装配失效。
 * 2,查看DispatchServlet下面的matchingBeans 有9 个，最主要的是 resourceHandlerMapping, 所以可以不用@EnableWebMvc,自己注入一个rhm.
 * 3,resourceHandlerMapping来源于，WebMvcConfigurationSupport.
 * 3,测试不成功，中间变量保护，不太好弄。
 *//*

@EnableWebMvc
public class SwaggerConfig extends WebMvcConfigurerAdapter {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .build();
    }

    */
/*@Bean
    public HandlerMapping resourceHandlerMapping() {
        ResourceHandlerRegistry registry = new ResourceHandlerRegistry(this.applicationContext,
                this.servletContext, mvcContentNegotiationManager());
        addResourceHandlers(registry);

        AbstractHandlerMapping handlerMapping = registry.getHandlerMapping();
        if (handlerMapping != null) {
            handlerMapping.setPathMatcher(mvcPathMatcher());
            handlerMapping.setUrlPathHelper(mvcUrlPathHelper());
            handlerMapping.setInterceptors(new ResourceUrlProviderExposingInterceptor(mvcResourceUrlProvider()));
            handlerMapping.setCorsConfigurations(getCorsConfigurations());
        }
        else {
            handlerMapping = new WebMvcConfigurationSupport.EmptyHandlerMapping();
        }
        return handlerMapping;
    }

    public ContentNegotiationManager mvcContentNegotiationManager() {
        if (this.contentNegotiationManager == null) {
            try {
                ContentNegotiationManagerFactoryBean factory =
                        new ContentNegotiationManagerFactoryBean();
                Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>(){{
                    put("atom", MediaType.APPLICATION_ATOM_XML);
                    put("rss", MediaType.APPLICATION_RSS_XML);
                    put("xml", MediaType.APPLICATION_XML);
                    put("json", MediaType.APPLICATION_JSON);
                }};
                factory.addMediaTypes(mediaTypes);
                factory.afterPropertiesSet();

                this.contentNegotiationManager = factory.getObject();
            }
            catch (Exception ex) {
                throw new BeanInitializationException("Could not create ContentNegotiationManager", ex);
            }
        }
        return this.contentNegotiationManager;
    }*//*



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
*/
