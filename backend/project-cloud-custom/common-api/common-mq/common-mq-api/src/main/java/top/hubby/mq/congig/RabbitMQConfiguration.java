package top.hubby.mq.congig;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.springframework.amqp.support.converter.Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID;

/**
 * @author zack <br>
 * @create 2022-04-11 19:46 <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Getter
public class RabbitMQConfiguration {
    private RabbitTemplate rabbitTemplate;

    @Bean
    @ConditionalOnMissingBean
    public Jackson2JsonMessageConverter converter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setTypePrecedence(TYPE_ID);
        return converter;
    }

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public RabbitTemplate rabbitTemplate(
            CachingConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);

        rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter);
        initRabbitmqCallback();

        return rabbitTemplate;
    }

    private void initRabbitmqCallback() {

        // important
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(
                // ack: 这个表示是否成功发送到 broker
                (correlationData, ack, cause) -> {
                    log.info(
                            "ConfirmCallback: correlationData: {} ack: {} cause:{}",
                            correlationData,
                            ack,
                            cause);
                });

        rabbitTemplate.setReturnCallback(
                // 消息没有被发送到 queue 才会调用
                (message, replyCode, replyText, exchange, routingKey) -> {
                    log.info(
                            "ReturnCallback: Fail message: {} replyCode: {} replyText:{} exchange: {} routingKey: {}",
                            message,
                            replyCode,
                            replyText,
                            exchange,
                            routingKey);
                });
    }
}
