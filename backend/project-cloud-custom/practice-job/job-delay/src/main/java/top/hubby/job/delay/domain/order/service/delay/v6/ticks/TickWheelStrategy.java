package top.hubby.job.delay.domain.order.service.delay.v6.ticks;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;
import top.hubby.job.delay.domain.order.event.OrderInfoCreateEvent;
import top.hubby.job.delay.domain.order.service.OrderInfoService;

import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author asd <br>
 * @create 2021-12-03 2:47 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
// @Service
public class TickWheelStrategy {

    private static Timer TIMER = new HashedWheelTimer();
    @Resource private OrderInfoService orderInfoService;

    @TransactionalEventListener
    public void onOrderCreated(OrderInfoCreateEvent event) {
        // 添加定时任务
        TIMER.newTimeout(
                new CancelOrderTimerTask(event.getOrderInfo().getId()), 5, TimeUnit.SECONDS);
        log.info("Success to add cancel task for order {}", event.getOrderInfo().getId());
    }

    class CancelOrderTimerTask implements TimerTask {

        private long orderId;

        public CancelOrderTimerTask(long orderId) {
            this.orderId = orderId;
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            orderInfoService.cancel(orderId);
        }
    }
}
