package custom.basic.grpc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import custom.basic.grpc.aspect.FallbackAnnoAspect;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Custom annotation to handle rpc exception and do fallback logic.
 *
 * <p>// TODO: TYPE invalid
 *
 * @see FallbackAnnoAspect
 * @author zack <br>
 * @create 2021-06-28<br>
 * @project custom-basic-biz <br>
 */
@Target({TYPE, METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FallbackAnno {}
