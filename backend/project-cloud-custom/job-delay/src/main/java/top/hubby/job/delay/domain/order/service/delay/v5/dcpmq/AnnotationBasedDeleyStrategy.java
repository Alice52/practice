package top.hubby.job.delay.domain.order.service.delay.v5.dcpmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import top.hubby.job.delay.domain.order.event.OrderInfoCreateEvent;

/**
 * @author asd <br>
 * @create 2021-11-26 5:01 PM <br>
 * @project project-cloud-custom <br>
 */
@Service
@Slf4j
public class AnnotationBasedDeleyStrategy {
    @Autowired private RocketMQBasedDelayService delayService;

    @TransactionalEventListener
    public void onOrderCreated(OrderInfoCreateEvent event) {
        // 直接调用服务方法
        delayService.cancelOrder(event.getOrderInfo().getId());
        log.info(
                "success to sent Delay Task to RocketMQ for Cancel Order {}",
                event.getOrderInfo().getId());
    }
}
