package coding.ifelse.strategy.component;

import cn.hutool.core.lang.ClassScanner;
import coding.ifelse.strategy.annotation.HandlerType;
import coding.ifelse.strategy.context.OrderHandlerContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Init order handlers context to spring ioc:
 *
 * <pre>
 *    1. get all class marked by {@link HandlerType}
 * </pre>
 *
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Component
public class OrderHandlerProcessor implements BeanFactoryPostProcessor {

  private static final String HANDLER_PACKAGE = "coding.ifelse.strategy.handler";

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
      throws BeansException {

    Map<String, Class> handlerMap = new HashMap<>(3);
    ClassScanner.scanPackageByAnnotation(HANDLER_PACKAGE, HandlerType.class).stream()
        .forEach(x -> handlerMap.put(x.getAnnotation(HandlerType.class).value(), x));

    OrderHandlerContext context = new OrderHandlerContext(handlerMap);
    factory.registerSingleton(OrderHandlerContext.class.getName(), context);
  }
}
