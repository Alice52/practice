package top.hubby.coding.elseif.strategy.controller;

import javax.annotation.Resource;

import top.hubby.coding.elseif.strategy.model.to.OrderDTO;
import top.hubby.coding.elseif.strategy.service.IOrderService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@RestController
public class OrderController {

    @Resource IOrderService orderService;

    @PostMapping("/order")
    public String handler(@RequestBody OrderDTO order) {

        return orderService.handle(order);
    }
}
