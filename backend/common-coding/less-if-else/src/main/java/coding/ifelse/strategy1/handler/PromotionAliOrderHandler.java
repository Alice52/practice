package coding.ifelse.strategy1.handler;

import coding.ifelse.strategy.constants.OrderConstants;
import coding.ifelse.strategy.model.to.OrderDTO;
import coding.ifelse.strategy1.annotation.HandlerType;
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
