package cn.edu.ntu.common.api.autoconfiguration;

import cn.edu.ntu.common.api.interceptor.RequestInterceptor;
import cn.edu.ntu.common.api.properties.ResponseProperties;
import cn.edu.ntu.common.api.service.UnifiedMessageSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zack <br>
 * @create 2020/12/17 <br>
 * @project common-api <br>
 */
@ConditionalOnProperty(
    prefix = "common.response",
    value = {"enabled"},
    havingValue = "true",
    matchIfMissing = true)
@Configuration
@EnableConfigurationProperties(ResponseProperties.class)
@ComponentScan(
    basePackages = {
      "cn.edu.ntu.common.api.exception.handler",
      "cn.edu.ntu.common.api.response.configure",
    })
@Import({UnifiedMessageSource.class, RequestInterceptor.class
  // cannot import ResponseProperties.class
})
public class CommonApiAutoConfiguration {}
