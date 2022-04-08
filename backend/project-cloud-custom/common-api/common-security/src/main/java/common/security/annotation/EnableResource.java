package common.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import common.security.component.SecurityBeanDefinitionRegistrar;
import common.security.connfiguration.CustomFeignClientConfiguration;
import common.security.connfiguration.ResourceServerAutoConfiguration;

import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Mark resource server.
 *
 * @author asd <br>
 * @create 2021-06-29 2:52 PM <br>
 * @project custom-upms-grpc <br>
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({
    ResourceServerAutoConfiguration.class,
    SecurityBeanDefinitionRegistrar.class,
    CustomFeignClientConfiguration.class
})
public @interface EnableResource {}
