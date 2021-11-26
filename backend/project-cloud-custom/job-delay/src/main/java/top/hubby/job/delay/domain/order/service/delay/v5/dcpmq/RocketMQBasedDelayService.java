package top.hubby.job.delay.domain.order.service.delay.v5.dcpmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.hubby.job.delay.domain.order.service.OrderInfoService;
import top.hubby.job.delay.domain.order.service.delay.v5.dcpmq.support.annotation.RocketMQBasedDelay;

/**
 * @author asd <br>
 * @create 2021-11-26 4:55 PM <br>
 * @project project-cloud-custom <br>
 */
@Service
@Slf4j
public class RocketMQBasedDelayService {
    @Autowired private OrderInfoService orderInfoService;

    /**
     * 通过 RocketMQBasedDelay 指定方法为延时方法, 该 注解做两件事：<br>
     * 1. 基于 AOP 技术, 拦截对 cancelOrder 的调用, 将参数转为为 Message, 并发送到 RocketMQ 的延时队列<br>
     * 2. 针对 cancelOrder 方法, 创建 DefaultMQPushConsumer 并订阅相关消息, 进行消息处理<br>
     *
     * @param orderId
     */
    @RocketMQBasedDelay(
            topic = "delay-task-topic-ann",
            delayLevel = 2,
            consumerGroup = "CancelOrderGroup")
    public void cancelOrder(Long orderId) {
        if (orderId == null) {
            log.info("param is invalidate");
            return;
        }
        this.orderInfoService.cancel(orderId);
        log.info("success to cancel Order for {}", orderId);
    }
}
