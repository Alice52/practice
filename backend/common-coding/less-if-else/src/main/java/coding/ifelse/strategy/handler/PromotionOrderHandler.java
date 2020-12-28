package coding.ifelse.strategy.handler;

import coding.ifelse.strategy.annotation.HandlerType;
import coding.ifelse.strategy.constants.OrderTypeConstants;
import coding.ifelse.strategy.model.to.OrderDTO;
import org.springframework.stereotype.Component;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Component
@HandlerType(OrderTypeConstants.PROMOTION_ORDER)
public class PromotionOrderHandler extends AbstractOrderHandler {
  @Override
  public String handle(OrderDTO order) {
    return OrderTypeConstants.PROMOTION_ORDER;
  }
}
