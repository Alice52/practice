package coding.ifelse.strategy.handler;

import coding.ifelse.strategy.annotation.HandlerType;
import coding.ifelse.strategy.constants.OrderConstants;
import coding.ifelse.strategy.model.to.OrderDTO;
import org.springframework.stereotype.Component;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Component
@HandlerType(source = OrderConstants.TYPE_GROUP_ORDER)
public class GroupOrderHandler extends AbstractOrderHandler {
  @Override
  public String handle(OrderDTO order) {
    return OrderConstants.TYPE_GROUP_ORDER;
  }
}
