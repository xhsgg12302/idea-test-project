package site.wtfu.framework.config;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-04
 * @Desc:
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    /*@Value("${global.equipment.camera-trigger-save-pic-path}")
    private String picPath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new BasePathInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/asyncServlet")
                .excludePathPatterns("/")
                .excludePathPatterns("/admin/orderEnter/unattended")
                .excludePathPatterns("/_draft.test/*");
        super.addInterceptors(registry);
    }*/



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


        /* * 说明：增加虚拟路径(经过本人测试：在此处配置的虚拟路径，用springboot内置的tomcat时有效，
         * 用外部的tomcat也有效;所以用到外部的tomcat时不需在tomcat/config下的相应文件配置虚拟路径了,阿里云linux也没问题)*/

        String picLocation = "_base.file:" + "c";
        registry.addResourceHandler("/image/**").addResourceLocations(picLocation);
        super.addResourceHandlers(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        if(1==2){
            for (HttpMessageConverter converter : converters) {
                if (converter instanceof MappingJacksonHttpMessageConverter) {
                    ObjectMapper objectMapper = ((MappingJacksonHttpMessageConverter) converter).getObjectMapper();
                    SimpleModule module = new SimpleModule("localdateTime", Version.unknownVersion());
                    module.addSerializer(LocalDateTime.class,new LocalDateTimeSerializer());
                    module.addDeserializer(LocalDateTime.class,new LocalDateTimeDeserializer());
                    objectMapper.registerModule(module);
                }
            }
        }
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
            System.out.println(argumentResolver);
        }
    }

    private static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

        private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        @Override
        public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
                throws IOException {
            jgen.writeString(dateTimeFormatter.format(value));
        }

    }

    private static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String result = "";
            JsonToken curr = jsonParser.getCurrentToken();
            // Usually should just get string value:
            if (curr == JsonToken.VALUE_STRING) {
                result = jsonParser.getText();
            }
            return LocalDateTime.parse(result);
        }
    }


}
