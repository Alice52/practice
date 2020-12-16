package cn.edu.ntu.common.api.configuration;

import cn.edu.ntu.common.api.service.UnifiedMessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author zack <br>
 * @create 2020/12/17 <br>
 * @project common-api <br>
 */
@Configuration
@ComponentScan(
    basePackages = {
      "cn.edu.ntu.common.api.exception.handler",
      "cn.edu.ntu.common.api.response.configure"
    })
@Import(UnifiedMessageSource.class)
public class CommonApiAutoConfiguration {}
