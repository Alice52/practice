package coding.ifelse.strategy1.context;

import cn.hutool.core.lang.Assert;
import coding.ifelse.strategy.model.to.OrderDTO;
import coding.ifelse.strategy1.annotation.HandlerType;
import coding.ifelse.strategy1.annotation.HandlerTypeImpl;
import coding.ifelse.strategy1.handler.AbstractOrderHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Component
public class OrderHandlerContext {

  private Map<HandlerType, AbstractOrderHandler> handlerMap;

  /**
   * Use HandlerType as key.
   *
   * @param orderHandlers
   */
  @Autowired
  public OrderHandlerContext(List<AbstractOrderHandler> orderHandlers) {

    handlerMap =
        orderHandlers.stream()
            .collect(
                Collectors.toMap(
                    orderHandler ->
                        AnnotationUtils.findAnnotation(orderHandler.getClass(), HandlerType.class),
                    v -> v,
                    (v1, v2) -> v1));
  }

  /**
   * Get different bean for order handle.
   *
   * @param order
   * @return
   */
  public AbstractOrderHandler getInstance(OrderDTO order) {

    HandlerType orderHandlerType = new HandlerTypeImpl(order.getType(), order.getPay());

    Assert.notNull(
        orderHandlerType,
        "not found handler for type: {}, pay: {}",
        order.getType(),
        order.getPay());

    return handlerMap.get(orderHandlerType);
  }
}
