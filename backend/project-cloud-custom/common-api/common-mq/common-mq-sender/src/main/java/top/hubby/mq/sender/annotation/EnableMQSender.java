package top.hubby.mq.sender.annotation;

import org.springframework.context.annotation.Import;
import top.hubby.mq.sender.configuration.RabbitMQAutoConfiguration;

import java.lang.annotation.*;

/**
 * @author zack <br>
 * @create 2022-04-11 20:09 <br>
 * @project project-cloud-custom <br>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({RabbitMQAutoConfiguration.class})
public @interface EnableMQSender {}
