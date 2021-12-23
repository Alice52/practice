package top.hubby.coding.elseif.strategy.service.impl;

import top.hubby.coding.elseif.strategy.constants.OrderConstants;
import top.hubby.coding.elseif.strategy.model.to.OrderDTO;
import top.hubby.coding.elseif.strategy.service.IOrderService;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
// @Service
public class BeforeOrderService implements IOrderService {
    @Override
    public String handle(OrderDTO order) {

        String type = order.getType();

        if (type == OrderConstants.TYPE_NORMAL_ORDER) {
            return OrderConstants.TYPE_NORMAL_ORDER;
        } else if (type == OrderConstants.TYPE_GROUP_ORDER) {
            return OrderConstants.TYPE_GROUP_ORDER;
        } else if (type == OrderConstants.TYPE_PROMOTION_ORDER) {
            return OrderConstants.TYPE_PROMOTION_ORDER;
        }

        return null;
    }
}
