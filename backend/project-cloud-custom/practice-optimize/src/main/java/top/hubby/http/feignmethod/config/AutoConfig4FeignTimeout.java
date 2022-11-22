package top.hubby.http.feignmethod.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author asd <br>
 * @create 2021-10-22 4:55 PM <br>
 * @project swagger-3 <br>
 */
@Configuration
@EnableFeignClients(basePackages = "top.hubby.http.feignmethod.client")
public class AutoConfig4FeignTimeout {}
