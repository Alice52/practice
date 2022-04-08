package top.hubby.job.delay.domain.order.service.delay.v2.memorydelay;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import top.hubby.job.delay.domain.order.event.OrderInfoCreateEvent;
import top.hubby.job.delay.domain.order.service.OrderInfoService;

import org.springframework.context.SmartLifecycle;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author asd <br>
 * @create 2021-11-26 2:53 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
// @Service
public class DelayQueueStrategy implements SmartLifecycle {
    private final DelayQueue<DelayTask> delayTasks = new DelayQueue<>();
    private final Thread thread = new OrderCancelWorker();
    private boolean running;
    @Resource private OrderInfoService orderInfoService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onOrderCreated(OrderInfoCreateEvent event) {
        // 将 订单号 放入延时队列
        this.delayTasks.offer(new DelayTask(event.getOrderInfo().getId(), 60 * 2));
        log.info("success to add Delay Task for Cancel Order {}", event.getOrderInfo().getId());
    }

    /** 启动后台线程，消费延时队列中的任务 */
    @Override
    public void start() {
        if (this.running) {
            return;
        }
        this.thread.start();

        this.running = true;
    }

    /** 停止后台线程 */
    @Override
    public void stop() {
        if (!this.running) {
            return;
        }
        this.thread.interrupt();
        this.running = false;
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    /** 延时任务 */
    @Value
    private static class DelayTask implements Delayed {
        private final Long orderId;
        private final LocalDateTime runAt;

        private DelayTask(Long orderId, int delayTime) {
            this.orderId = orderId;
            this.runAt = LocalDateTime.now().plusSeconds(delayTime);
        }

        /**
         * 获取剩余时间
         *
         * @param timeUnit
         * @return
         */
        @Override
        public long getDelay(TimeUnit timeUnit) {
            Instant instant = getRunAt().atZone(ZoneId.systemDefault()).toInstant();
            return timeUnit.convert(
                    instant.toEpochMilli() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed delayed) {
            if (delayed == this) {
                return 0;
            } else {
                long d =
                        this.getDelay(TimeUnit.NANOSECONDS)
                                - delayed.getDelay(TimeUnit.NANOSECONDS);
                return Long.compare(d, 0L);
            }
        }
    }

    /** 后台线程，消费延时队列中的消息 */
    private class OrderCancelWorker extends Thread {
        @Override
        public void run() {
            // 根据中断状态，确定是否退出
            while (!Thread.currentThread().isInterrupted()) {
                DelayTask task = null;
                try {
                    // 从队列中获取任务
                    task = delayTasks.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 取消订单
                if (task != null) {
                    orderInfoService.cancel(task.getOrderId());
                    log.info("Success to Run Delay Task, Cancel Order {}", task.getOrderId());
                }
            }
        }
    }
}
