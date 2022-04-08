package top.hubby.http.robbinretry.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author asd <br>
 * @create 2021-10-22 4:27 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Configuration
@EnableFeignClients(basePackages = "top.hubby.http.robbinretry.feign")
public class AutoConfig {}
