package top.hubby.coding.elseif.strategy.service.impl;

import javax.annotation.Resource;

import top.hubby.coding.elseif.strategy.context.OrderHandlerContext;
import top.hubby.coding.elseif.strategy.handler.AbstractOrderHandler;
import top.hubby.coding.elseif.strategy.model.to.OrderDTO;
import top.hubby.coding.elseif.strategy.service.IOrderService;

import org.springframework.stereotype.Service;

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
