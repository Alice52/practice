package cn.edu.ntu.common.api.autoconfiguration;

import cn.edu.ntu.common.api.interceptor.RequestInterceptor;
import cn.edu.ntu.common.api.properties.ResponseProperties;
import cn.edu.ntu.common.api.service.UnifiedMessageSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
public class CommonApiAutoConfiguration implements WebMvcConfigurer {

  @Value("${common.response.advice.allow-string:true}")
  private boolean allowString;

  /**
   * This method is for handle api response is string type.
   *
   * <pre>
   *     1. root cause is due to jackson
   *     2. due to in HttpMessageConverter list, the StringHttpMessageConverter is high priority than other Converters.
   *     3. so we need to make handle Object type HttpMessageConverter before than handle string type in Configuration
   *
   * </pre>
   *
   * @param converters
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    if (allowString) {
      converters.add(0, new MappingJackson2HttpMessageConverter());
    }
  }
}
