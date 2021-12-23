package top.hubby.coding.elseif.strategy.context;

import cn.hutool.core.lang.Assert;
import top.hubby.coding.elseif.strategy.component.SpringContextUtils;
import top.hubby.coding.elseif.strategy.handler.AbstractOrderHandler;

import java.util.Map;

/**
 * This class is customized component and will be inject to ioc by {@link
 * top.hubby.coding.elseif.strategy.component.OrderHandlerProcessor}
 *
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public class OrderHandlerContext {

    private Map<String, Class> handlerMap;

    public OrderHandlerContext(Map<String, Class> handlerMap) {
        this.handlerMap = handlerMap;
    }

    /**
     * Get different bean for order handle.
     *
     * @param type
     * @return
     */
    public AbstractOrderHandler getInstance(String type) {

        Class clazz = handlerMap.get(type);
        Assert.notNull(clazz, "not found handler for type: {}", type);
        Object bean = SpringContextUtils.getBeansOfTypeOfTop1(clazz);
        Assert.notNull(bean, "not got bean of type: {}", clazz);

        return (AbstractOrderHandler) bean;
    }
}
