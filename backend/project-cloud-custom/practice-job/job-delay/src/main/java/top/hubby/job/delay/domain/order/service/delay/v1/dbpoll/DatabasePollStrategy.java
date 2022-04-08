package top.hubby.job.delay.domain.order.service.delay.v1.dbpoll;

import java.time.LocalDateTime;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import top.hubby.job.delay.domain.order.repository.po.OrderInfo;
import top.hubby.job.delay.domain.order.service.OrderInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author asd <br>
 * @create 2021-11-26 2:03 PM <br>
 * @project project-cloud-custom <br>
 */
// @Service
@Slf4j
public class DatabasePollStrategy {
    @Autowired private OrderInfoService orderInfoService;

    /**
     * 每隔 1S 运行一次 <br>
     * 1. 从 DB 中查询过期未支付订单（状态为 CREATED，创建时间小于 deadLintDate）<br>
     * 2. 依次执行 取消订单 操作
     */
    @Scheduled(fixedDelay = 1 * 1000)
    public void poll() {
        LocalDateTime overTimeThreshold = LocalDateTime.now().minusMinutes(30);
        List<OrderInfo> overtimeNotPaidOrders =
                orderInfoService.findOvertimeNotPaidOrders(overTimeThreshold);
        log.info("load overtime Not paid orders {}", overtimeNotPaidOrders);
        overtimeNotPaidOrders.forEach(orderInfo -> this.orderInfoService.cancel(orderInfo.getId()));
    }
}
