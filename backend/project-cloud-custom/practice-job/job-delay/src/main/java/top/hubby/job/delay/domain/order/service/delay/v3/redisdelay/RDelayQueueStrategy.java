package top.hubby.job.delay.domain.order.service.delay.v3.redisdelay;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.springframework.context.SmartLifecycle;
import org.springframework.transaction.event.TransactionalEventListener;
import top.hubby.job.delay.domain.order.event.OrderInfoCreateEvent;
import top.hubby.job.delay.domain.order.service.OrderInfoService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-11-26 3:45 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
// @Service
public class RDelayQueueStrategy implements SmartLifecycle {
    private boolean running;
    private Thread thread = new OrderCancelWorker();
    // 不是有 DelayTask: 因为 RDelayedQueue 不支持 put DelayTask操作
    private RBlockingQueue<Long> cancelOrderQueue;
    private RDelayedQueue<Long> delayedQueue;

    @Resource private OrderInfoService orderInfoService;
    @Resource private RedissonClient redissonClient;

    /** 创建 Redis 队列 */
    @PostConstruct
    public void init() {
        this.cancelOrderQueue = redissonClient.getBlockingQueue("DelayQueueForCancelOrder");
        this.delayedQueue = redissonClient.getDelayedQueue(cancelOrderQueue);
    }

    @TransactionalEventListener
    public void onOrderCreated(OrderInfoCreateEvent event) {
        this.delayedQueue.offer(event.getOrderInfo().getId(), 5, TimeUnit.SECONDS);
        log.info("success to add Delay Task for Cancel Order {}", event.getOrderInfo().getId());
    }

    /** 启动后台线程 */
    @Override
    public void start() {
        if (this.running) {
            return;
        }
        thread.start();
        this.running = true;
    }

    /** 停止后台线程 */
    @Override
    public void stop() {
        if (!this.running) {
            return;
        }
        thread.interrupt();
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

    private class OrderCancelWorker extends Thread {
        @Override
        public void run() {
            // 根据中断状态，确定是否退出
            while (!Thread.currentThread().isInterrupted()) {
                Long taskId = null;
                try {
                    // 从队列中获取 订单号
                    taskId = cancelOrderQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 取消订单
                if (taskId != null) {
                    orderInfoService.cancel(taskId);
                    log.info("Success to Run Delay Task, Cancel Order {}", taskId);
                }
            }
        }
    }
}
