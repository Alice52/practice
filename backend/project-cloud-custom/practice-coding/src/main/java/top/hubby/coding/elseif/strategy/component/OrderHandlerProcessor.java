package top.hubby.coding.elseif.strategy.component;

import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.lang.ClassScanner;
import top.hubby.coding.elseif.strategy.annotation.HandlerType;
import top.hubby.coding.elseif.strategy.context.OrderHandlerContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * core thinking: get map, which key is handler-type and value is XxOrderHandler.<br>
 * Init order handlers context to spring ioc:
 *
 * <pre>
 *    1. get all class marked by {@link HandlerType}
 *    2. create {@link OrderHandlerContext } and put to ioc.
 * </pre>
 *
 * <pre>
 *    1. this class can be replaced by follow code in {@ link OrderHandlerContext }
 *
 *     private Map<String, AbstractOrderHandler> orderHandleMap;
 *     @Autowired
 *     public void setOrderHandleMap(List<AbstractOrderHandler> orderHandlers) {
 *         orderHandleMap = orderHandlers.stream().collect(
 *                 Collectors.toMap(orderHandler -> AnnotationUtils.findAnnotation(orderHandler.getClass(), HandlerType.class).source(),
 *                      v -> v, (v1, v2) -> v1));
 *     }
 * </pre>
 *
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Component
public class OrderHandlerProcessor implements BeanFactoryPostProcessor {

    private static final String HANDLER_PACKAGE = "top.hubby.coding.elseif.strategy.handler";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
            throws BeansException {

        Map<String, Class> handlerMap = new HashMap<>(3);
        ClassScanner.scanPackageByAnnotation(HANDLER_PACKAGE, HandlerType.class).stream()
                .forEach(x -> handlerMap.put(x.getAnnotation(HandlerType.class).source(), x));

        OrderHandlerContext context = new OrderHandlerContext(handlerMap);
        factory.registerSingleton(OrderHandlerContext.class.getName(), context);
    }
}
