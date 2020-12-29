package coding.ifelse.strategy1.service.impl;

import coding.ifelse.strategy.model.to.OrderDTO;
import coding.ifelse.strategy1.context.OrderHandlerContext;
import coding.ifelse.strategy1.handler.AbstractOrderHandler;
import coding.ifelse.strategy1.service.IOrderService;
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
    AbstractOrderHandler instance = context.getInstance(order);

    return instance.handle(order);
  }
}
