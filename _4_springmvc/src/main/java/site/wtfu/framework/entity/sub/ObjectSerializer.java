package site.wtfu.framework.entity.sub;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/5
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class ObjectSerializer extends JsonSerializer<Proxy> {

    @Override
    public void serialize(Proxy value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException {
        if (gen instanceof ExYAMLGenerator) {

            ((ExYAMLGenerator) gen).writeStartCObject();


            Field[] selfs = value.getClass().getDeclaredFields();
            Field[] supers = value.getClass().getSuperclass().getDeclaredFields();

            Field[] all = Stream.concat(Arrays.stream(selfs), Arrays.stream(supers)).toArray(Field[]::new);

            for (Field field : all) {
                field.setAccessible(true);
                try {
                    String key = field.getName();
                    Object val = field.get(value);

                    if(val == null) continue;

                    JsonProperty annotation = field.getAnnotation(JsonProperty.class);
                    if(annotation != null){ key = annotation.value(); }

                    if("name".equals(key)) {
                        gen.writeFieldName(key);
                        ((ExYAMLGenerator) gen).writeQuotedString(val.toString());
                        continue;
                    }

                    gen.writeObjectField(key, val);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            gen.writeEndObject();
        } else {
            // 对于非YAML的情况，你可以选择合适的默认行为，比如正常序列化或抛出异常
            throw new UnsupportedOperationException("This serializer is intended for YAML output only.");
        }
    }
}
