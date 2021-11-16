package common.core.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author zack <br>
 * @create 2021-06-01<br>
 * @project project-custom <br>
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(MappingJackson2HttpMessageConverter converter) {
        return new RestTemplateBuilder().additionalMessageConverters(converter).build();
    }
}
