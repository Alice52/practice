package top.hubby.job.delay.domain.order.service.delay.v4.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;
import top.hubby.job.delay.domain.order.service.OrderInfoService;

import javax.annotation.Resource;

/**
 * @author asd <br>
 * @create 2021-11-26 4:09 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
// @Service
// @RocketMQMessageListener(topic = "delay-task-topic", consumerGroup = "delay-task-consumer-group")
public class RocketMQBasedDelayTaskConsumer implements RocketMQListener<MessageExt> {
    @Resource private OrderInfoService orderInfoService;

    /**
     * 接收消息回调，执行取消订单操作
     *
     * @param message
     */
    @Override
    public void onMessage(MessageExt message) {
        byte[] body = message.getBody();
        String idAsStr = new String(body);
        orderInfoService.cancel(Long.valueOf(idAsStr));
    }
}
