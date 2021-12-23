package top.hubby.job.delay.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hubby.job.delay.domain.order.service.OrderInfoService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author asd <br>
 * @create 2021-11-26 1:27 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@RestController
@RequestMapping("/job/delay/order")
public class OrderController {
    @Resource private OrderInfoService orderInfoService;

    /** 生成新的订单，主要用于测试 */
    @PostMapping("insertTestData")
    public void createTestOrder() {
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(30).plusSeconds(10);
        this.orderInfoService.create(dateTime);
    }
}
