package top.hubby.job.delay.domain.order.service.delay.v5.dcpmq.support.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-11-26 4:58 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
public class SerializeUtil {
    public static final ObjectMapper MAPPER;

    @SneakyThrows
    public static String serialize(Object obj) {
        return MAPPER.writeValueAsString(obj);
    }

    @SneakyThrows
    public static <T> T deserialize(String jsonText, Class<T> beanClass) {
        return MAPPER.readValue(jsonText, beanClass);
    }

    static {
        MAPPER = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}
