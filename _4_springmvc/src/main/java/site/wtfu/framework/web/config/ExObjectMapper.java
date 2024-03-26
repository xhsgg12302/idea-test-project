package site.wtfu.framework.web.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import site.wtfu.framework.entity.User;

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
public class ExObjectMapper extends ObjectMapper {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ExObjectMapper(){
        super();
        Jackson2ObjectMapperBuilder.json().configure(this);

        SimpleModule simpleModule = new SimpleModule("custom", VersionUtil.versionFor(ExObjectMapper.class));
        simpleModule.addSerializer(User.class, new UserSerializer());
        simpleModule.addDeserializer(User.class, new UserDeserializer());

        simpleModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        this.registerModule(simpleModule);
    }

    private static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
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

            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("name", value.getName() + "_serialized");
            jsonGenerator.writeNumberField("age",value.getAge());
            jsonGenerator.writeStringField("account",value.getAccount());
            jsonGenerator.writeStringField("password", value.getPassword());
            jsonGenerator.writeStringField("time", value.getTime() != null ? value.getTime().toString() : null);
            jsonGenerator.writeStringField("now", value.getNow() != null ? value.getNow().toString() : null);
            jsonGenerator.writeEndObject();
        }

    }

    private static class UserDeserializer extends JsonDeserializer<User> {

        @Override
        public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

            User user = new User();
            JsonNode jsonNode = jsonParser.readValueAsTree();

            JsonNode name = jsonNode.get("name");
            JsonNode age = jsonNode.get("age");
            JsonNode account = jsonNode.get("account");
            JsonNode pass = jsonNode.get("password");
            JsonNode time = jsonNode.get("time");
            user.setName(name != null ? name.asText() + "_deserialized" : null);
            user.setAge(age != null ? age.asInt() : 0);
            user.setAccount(account != null ? account.asText() : null);
            user.setPassword(pass != null ? pass.asText() : null);

            LocalDateTime ldt = null;
            try{
                if(time != null){ldt = LocalDateTime.parse(time.asText());}
            }catch (Exception ignored){}

            user.setTime(ldt);
            user.setNow(LocalDateTime.now());
            return user;
        }
    }

}

