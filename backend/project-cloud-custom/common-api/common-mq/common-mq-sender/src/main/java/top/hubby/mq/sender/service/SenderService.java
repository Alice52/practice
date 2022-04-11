package top.hubby.mq.sender.service;

import cn.hutool.core.util.IdUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import top.hubby.mq.sender.configuration.props.MQProps;

import javax.annotation.Resource;

import static top.hubby.mq.sender.configuration.RabbitMQAutoConfiguration.mqSender;

/**
 * https://blog.csdn.net/xhf852963/article/details/107884528
 *
 * @see org.springframework.amqp.rabbit.connection.PublisherCallbackChannel
 * @author zack <br>
 * @create 2022-04-11 20:51 <br>
 * @project project-cloud-custom <br>
 */
@Service
@EnableConfigurationProperties(MQProps.class)
public class SenderService {

    @Resource private MQProps props;

    public void convertAndSend(final Object message) throws AmqpException {

        convertAndSend(message, IdUtil.fastUUID());
    }

    public void convertAndSend(final Object message, String uid) throws AmqpException {

        mqSender.convertAndSend(
                props.getExchange(),
                props.getRoutingKey(),
                message,
                m -> {
                    m.getMessageProperties().setCorrelationId(uid);
                    return m;
                },
                new CorrelationData(uid));
    }
}
