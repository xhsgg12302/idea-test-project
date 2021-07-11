package site.wtfu.framework.config;

import site.wtfu.framework.entity.User;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.*;
import org.codehaus.jackson.map.module.SimpleModule;
import org.codehaus.jackson.util.VersionUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-06
 * @Desc:
 */
public class EObjectMapper extends ObjectMapper {
    public EObjectMapper(){
        super();
        SimpleModule simpleModule = new SimpleModule("User", VersionUtil.versionFor(EObjectMapper.class));
        simpleModule.addSerializer(User.class, new UserSerializer());
        simpleModule.addDeserializer(User.class, new UserDeserializer());
        this.registerModule(simpleModule);
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

    private static class UserSerializer extends JsonSerializer<User> {

        @Override
        public void serialize(User value, JsonGenerator jsonGenerator, SerializerProvider provider)
                throws IOException {

            /**
             * _12302_2019-11-06
             * private String name;
             * 	private Integer age;
             * 	private String account;
             * 	private String password;
             * 	private LocalDateTime time;
             */
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", value.getName());
            jsonGenerator.writeNumberField("age",value.getAge());
            jsonGenerator.writeStringField("account",value.getAccount());
            jsonGenerator.writeStringField("password", value.getPassword());
            jsonGenerator.writeStringField("time",value.getTime().toString().replaceAll("T",""));
            jsonGenerator.writeEndObject();
        }

    }

    private static class UserDeserializer extends JsonDeserializer<User> {

        @Override
        public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

            User user = new User();
            JsonNode jsonNode = jsonParser.readValueAsTree();

            String name = jsonNode.get("name").asText();
            int age = jsonNode.get("age").asInt();
            String account = jsonNode.get("account").asText();
            String password = jsonNode.get("password").asText();
            LocalDateTime time = LocalDateTime.parse(jsonNode.get("time").asText());
            user.setName(name);
            user.setAge(age);
            user.setAccount(account);
            user.setPassword(password);
            user.setTime(time);
            return user;
        }
    }

}

