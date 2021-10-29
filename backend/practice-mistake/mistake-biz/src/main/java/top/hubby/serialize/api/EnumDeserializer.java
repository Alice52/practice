package top.hubby.serialize.api;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author asd <br>
 * @create 2021-10-29 3:42 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EnumDeserializer extends JsonDeserializer<Enum> implements ContextualDeserializer {

    private Class<Enum> targetClass;

    @Override
    public Enum deserialize(JsonParser p, DeserializationContext ctxt) {
        // 找枚举中带有@JsonValue注解的字段，这个字段是我们反序列化的基准字段
        Optional<Field> valueFieldOpt = Arrays.asList(targetClass.getDeclaredFields()).stream()
                .filter(m -> m.isAnnotationPresent(JsonValue.class)).findFirst();

        if (valueFieldOpt.isPresent()) {
            Field valueField = valueFieldOpt.get();
            if (!valueField.isAccessible()) {
                valueField.setAccessible(true);
            }
            // 遍历枚举项，查找字段的值等于反序列化的字符串的那个枚举项
            return Arrays.stream(targetClass.getEnumConstants())
                    .filter(e -> {
                                try {
                                    return valueField.get(e).toString().equals(p.getValueAsString());
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                return false;
                            })
                    .findFirst()
                    .orElseGet(() -> Arrays.stream(targetClass.getEnumConstants())
                            .filter(e -> {
                                // 如果找不到，那么就需要寻找默认枚举值来替代，同样遍历所有枚举项，查找@JsonEnumDefaultValue注解标识的枚举项
                                try {
                                    return targetClass.getField(e.name()).isAnnotationPresent(JsonEnumDefaultValue.class);
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                return false;
                            })
                            .findFirst().orElse(null));
        }

        return null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
            throws JsonMappingException {
        targetClass = (Class<Enum>) ctxt.getContextualType().getRawClass();
        return new EnumDeserializer(targetClass);
    }
}
