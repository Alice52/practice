package top.hubby.openapi.annotation;

import java.lang.annotation.*;

/**
 * please notice if use this api, the OpenApiHttpServletFilter should be injected for read request
 * body.
 *
 * @see com.mc4cloud.mc.common.core.component.OpenApiHttpServletFilter
 * @see com.mc4cloud.mc.common.core.aspect.OpenApiSignatureAspect
 * @author zack <br>
 * @create 2022-04-07 18:34 <br>
 * @project mc-platform <br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OpenApiSignature {}
