package top.hubby.coding.elseif.strategy.handler;

import org.springframework.stereotype.Component;
import top.hubby.coding.elseif.strategy.annotation.HandlerType;
import top.hubby.coding.elseif.strategy.constants.OrderConstants;
import top.hubby.coding.elseif.strategy.model.to.OrderDTO;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@HandlerType(source = OrderConstants.TYPE_NORMAL_ORDER)
@Component
public class NormalOrderHandler extends AbstractOrderHandler {
    @Override
    public String handle(OrderDTO order) {
        return OrderConstants.TYPE_NORMAL_ORDER;
    }
}
