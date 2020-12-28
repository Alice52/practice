package coding.ifelse.strategy.service;

import coding.ifelse.strategy.model.to.OrderDTO;

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
