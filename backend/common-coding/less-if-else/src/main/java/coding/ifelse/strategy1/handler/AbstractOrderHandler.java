package coding.ifelse.strategy1.handler;

import coding.ifelse.strategy.model.to.OrderDTO;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public abstract class AbstractOrderHandler {

  /**
   * Handle different order.
   *
   * @param order
   * @return
   */
  public abstract String handle(OrderDTO order);
}
