package top.hubby.coding.elseif.strategy1.service.impl;

import org.springframework.stereotype.Service;
import top.hubby.coding.elseif.strategy.model.to.OrderDTO;
import top.hubby.coding.elseif.strategy1.context.OrderHandlerContext;
import top.hubby.coding.elseif.strategy1.handler.AbstractOrderHandler;
import top.hubby.coding.elseif.strategy1.service.IOrderService;

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
