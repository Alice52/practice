package top.hubby.coding.elseif.strategy1.annotation;

import java.lang.annotation.*;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface HandlerType {
    String source();

    String pay() default "";
}
