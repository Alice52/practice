package top.hubby.grao;

import java.lang.annotation.*;

/**
 * @author asd <br>
 * @create 2021-11-12 3:18 PM <br>
 * @project swagger-3 <br>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface MyAnnotation {
    String value();
}
