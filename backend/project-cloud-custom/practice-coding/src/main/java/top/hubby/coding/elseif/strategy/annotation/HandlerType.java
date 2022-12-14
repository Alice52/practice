package top.hubby.coding.elseif.strategy.annotation;

import top.hubby.coding.elseif.strategy.component.OrderHandlerProcessor;

import java.lang.annotation.*;

/**
 * @see OrderHandlerProcessor
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface HandlerType {
    String source();
}
