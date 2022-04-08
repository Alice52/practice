package top.hubby.job.delay.domain.order.service.delay.v3.redisdelay;

import java.util.Calendar;
import java.util.Set;

import javax.annotation.Resource;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import top.hubby.job.delay.domain.order.event.OrderInfoCreateEvent;
import top.hubby.job.delay.domain.order.service.OrderInfoService;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author asd <br>
 * @create 2021-12-03 3:37 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
// @Service
public class ZsetPollStrategy {

    private static final String LABEL = "ORDER_ID";
    @Resource private OrderInfoService orderInfoService;
    @Resource private RedisTemplate<String, Number> redisTemplate;

    @TransactionalEventListener
    public void onOrderCreated(OrderInfoCreateEvent event) {

        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.SECOND, 5);
        int second5later = (int) (cal1.getTimeInMillis() / 1000);
        redisTemplate.opsForZSet().add(LABEL, event.getOrderInfo().getId(), second5later);
        log.info("success to add Delay Task for Cancel Order {}", event.getOrderInfo().getId());
    }

    @Scheduled(fixedDelay = 1 * 1000)
    @SneakyThrows
    public void consumerDelayMessage() {
        Set<ZSetOperations.TypedTuple<Number>> tuples =
                redisTemplate.opsForZSet().rangeWithScores(LABEL, 0, 1);
        if (tuples == null || tuples.isEmpty()) {
            return;
        }

        ZSetOperations.TypedTuple<Number> tuple = tuples.stream().findFirst().get();
        Double score = tuple.getScore();
        Calendar cal = Calendar.getInstance();
        int nowSecond = (int) (cal.getTimeInMillis() / 1000);

        if (nowSecond >= score) {
            long orderId = tuple.getValue().longValue();
            Long remove = redisTemplate.opsForZSet().remove(LABEL, orderId);
            if (remove != null && remove > 0) {
                orderInfoService.cancel(orderId);
            }
        }
    }
}
