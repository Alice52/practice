package coding.ifelse.strategy.controller;

import coding.ifelse.strategy.model.to.OrderDTO;
import coding.ifelse.strategy.service.IOrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
