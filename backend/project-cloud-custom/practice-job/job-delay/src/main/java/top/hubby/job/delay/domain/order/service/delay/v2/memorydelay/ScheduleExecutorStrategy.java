package top.hubby.job.delay.domain.order.service.delay.v2.memorydelay;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import jodd.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import top.hubby.job.delay.domain.order.event.OrderInfoCreateEvent;
import top.hubby.job.delay.domain.order.service.OrderInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author asd <br>
 * @create 2021-11-26 2:53 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
// @Service
public class ScheduleExecutorStrategy {
    @Autowired private OrderInfoService orderInfoService;

    private ScheduledExecutorService scheduledExecutorService;

    public ScheduleExecutorStrategy() {
        ThreadFactory basicThreadFactory =
                ThreadUtil.daemonThreadFactory("Schedule-Cancel-Thread-%d");
        this.scheduledExecutorService = new ScheduledThreadPoolExecutor(1, basicThreadFactory);
    }

    @TransactionalEventListener
    public void onOrderCreated(OrderInfoCreateEvent event) {
        // 添加定时任务
        this.scheduledExecutorService.schedule(
                new CancelTask(event.getOrderInfo().getId()), 5, TimeUnit.SECONDS);
        log.info("Success to add cancel task for order {}", event.getOrderInfo().getId());
    }

    private class CancelTask implements Runnable {
        private final Long orderId;

        private CancelTask(Long orderId) {
            this.orderId = orderId;
        }

        @Override
        public void run() {
            // 执行订单取消操作
            orderInfoService.cancel(this.orderId);
            log.info("Success to cancel task for order {}", this.orderId);
        }
    }
}
