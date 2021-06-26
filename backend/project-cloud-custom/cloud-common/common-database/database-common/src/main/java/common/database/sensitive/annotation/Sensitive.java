package common.database.sensitive.annotation;

import common.database.sensitive.SensitiveStrategy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这种方式很不好, 应该考虑使用 jackson 进行脱敏
 *
 * @author zack <br>
 * @create 2021-06-09 09:14 <br>
 * @project custom-test <br>
 */
@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Sensitive {
    SensitiveStrategy strategy();
}
