package top.hubby.coding.elseif.strategy.service;

import top.hubby.coding.elseif.strategy.model.to.OrderDTO;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public interface IOrderService {

    /**
     * Handle order by different type.
     *
     * @param order
     * @return String
     */
    String handle(OrderDTO order);
}
