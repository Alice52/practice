package coding.ifelse.strategy.service.impl;

import coding.ifelse.strategy.constants.OrderConstants;
import coding.ifelse.strategy.model.to.OrderDTO;
import coding.ifelse.strategy.service.IOrderService;

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
