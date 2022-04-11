package top.hubby.mq.sender.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import top.hubby.mq.congig.RabbitMQConfiguration;
import top.hubby.mq.sender.service.SenderService;

/**
 * @author zack <br>
 * @create 2022-04-11 19:40 <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@EnableRabbit
@Configuration
@ConditionalOnProperty(name = "common.mq.enable", matchIfMissing = true)
@ImportAutoConfiguration({DefaultQueueConfig.class, SenderService.class, QueueConfiguration.class})
public class RabbitMQAutoConfiguration extends RabbitMQConfiguration
        implements SmartInitializingSingleton {
    public static RabbitTemplate mqSender;

    @Override
    public void afterSingletonsInstantiated() {
        mqSender = this.getRabbitTemplate();
    }
}
