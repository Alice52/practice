package cn.edu.ntu.common.api.test.configuration;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zack <br>
 * @create 2020-10-05 19:11 <br>
 * @project project-ec <br>
 */
@ConditionalOnProperty(
    prefix = "druid.spring.monitor",
    value = {"enable"},
    havingValue = "true")
@Configuration
public class DruidConfiguration {

  @Value("#{'${druid.spring.monitor.locations:}'.split(',')}")
  private List<String> patterns;

  @Bean
  public DruidStatInterceptor druidStatInterceptor() {
    return new DruidStatInterceptor();
  }

  @Bean
  @Scope("prototype")
  public JdkRegexpMethodPointcut druidStatPointcut() {
    JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
    Optional.ofNullable(patterns)
        .ifPresent(
            x ->
                pointcut.setPatterns(
                    ArrayUtil.toArray(
                        x.stream().map(String::trim).collect(Collectors.toList()), String.class)));

    return pointcut;
  }

  @Bean
  public DefaultPointcutAdvisor druidStatAdvisor(
      DruidStatInterceptor druidStatInterceptor, JdkRegexpMethodPointcut druidStatPointcut) {
    return new DefaultPointcutAdvisor(druidStatPointcut, druidStatInterceptor);
  }
}
