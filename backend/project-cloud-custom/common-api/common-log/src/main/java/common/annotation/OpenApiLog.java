package common.annotation;

import java.lang.annotation.*;

/**
 * @author zack <br>
 * @create 2022-04-08 10:50 <br>
 * @project project-cloud-custom <br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpenApiLog {}
