package coding.ifelse.strategy.service.impl;

import coding.ifelse.strategy.constants.OrderTypeConstants;
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

    if (type == OrderTypeConstants.NORMAL_ORDER) {
      return OrderTypeConstants.NORMAL_ORDER;
    } else if (type == OrderTypeConstants.GROUP_ORDER) {
      return OrderTypeConstants.GROUP_ORDER;
    } else if (type == OrderTypeConstants.PROMOTION_ORDER) {
      return OrderTypeConstants.PROMOTION_ORDER;
    }

    return null;
  }
}
