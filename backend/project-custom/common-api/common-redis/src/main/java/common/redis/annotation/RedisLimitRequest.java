package common.redis.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * @author zack <br/>
 * @create 2021-06-04 16:21 <br/>
 * @project common-redis <br/>
 */
@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RedisLimitRequest {
    /**
     * 可以访问的次数
     *
     * @return
     */
    int count() default 0;

    /**
     * 指定的一段时间, 单位是 {@see TimeUnit.SECONDS}
     *
     * @return
     */
    long time() default 0;

    /**
     * 请求等待时间
     *
     * @return
     */
    long acquireTokenTimeout() default 0;

    /**
     * 请求等待时间的单位
     *
     * @return
     */
    TimeUnit acquireTokenTimeUnit() default TimeUnit.SECONDS;

    /**
     * 错误提示
     *
     * @return
     */
    String message() default "调用频繁";
}
