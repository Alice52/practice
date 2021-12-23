package top.hubby.job.delay.domain.order.event;

import lombok.Value;
import top.hubby.job.delay.domain.order.repository.po.OrderInfo;

@Value
public class OrderInfoCreateEvent {
    private OrderInfo orderInfo;
}
