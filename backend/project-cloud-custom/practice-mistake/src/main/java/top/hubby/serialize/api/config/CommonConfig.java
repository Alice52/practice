package top.hubby.serialize.api.config;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import common.core.configuration.JacksonConfig;
import common.core.configuration.RestTemplateConfig;
import common.core.jackson.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import top.hubby.serialize.api.EnumDeserializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author asd <br>
 * @create 2021-10-29 3:53 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Configuration
public class CommonConfig {

    /**
     * @see RestTemplateConfig
     * @param
     * @return
     */
    // @Bean
    // public RestTemplate restTemplate() {}

    /**
     * @see JacksonConfig
     * @return
     */
    @Bean
    public Module enumModule() {
        SimpleModule module = new JavaTimeModule();
        module.addDeserializer(Enum.class, new EnumDeserializer());
        return module;
    }
}
