package top.hubby.coding.elseif.strategy1.handler;

import top.hubby.coding.elseif.strategy.constants.OrderConstants;
import top.hubby.coding.elseif.strategy.model.to.OrderDTO;
import top.hubby.coding.elseif.strategy1.annotation.HandlerType;

import org.springframework.stereotype.Component;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Component
@HandlerType(
        source = OrderConstants.TYPE_PROMOTION_ORDER,
        pay = OrderConstants.TYPE_PROMOTION_ORDER)
public class PromotionAliOrderHandler extends AbstractOrderHandler {
    @Override
    public String handle(OrderDTO order) {
        return OrderConstants.TYPE_PROMOTION_ORDER;
    }
}
