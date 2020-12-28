package coding.ifelse.strategy.service.impl;

import coding.ifelse.strategy.context.OrderHandlerContext;
import coding.ifelse.strategy.handler.AbstractOrderHandler;
import coding.ifelse.strategy.model.to.OrderDTO;
import coding.ifelse.strategy.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Service
public class AfterOrderService implements IOrderService {

  @Resource OrderHandlerContext context;

  @Override
  public String handle(OrderDTO order) {
    AbstractOrderHandler instance = context.getInstance(order.getType());

    return instance.handle(order);
  }
}
