package custom.basic.grpc.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import custom.basic.grpc.annotation.FallbackAnno;
import io.grpc.StatusRuntimeException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

/**
 * @author zack <br>
 * @create 2021-06-28<br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class FallbackAnnoAspect {

    /**
     * Get fallback method, it has additional param of
     *
     * <pre>
     *     do like this is also OK.
     *   List<? extends Class<?>> argsTypes = Arrays.stream(point.getArgs()).map(Object::getClass).collect(Collectors.toList());
     *   point.getTarget().getClass().getMethod(point.getSignature().getName(), argsTypes.toArray(new Class[1]));
     * </pre>
     *
     * @param point
     * @param exception
     * @return
     */
    private static Optional<Method> getFallbackMethod(
            ProceedingJoinPoint point, StatusRuntimeException exception) {
        Class<?> clazz = point.getTarget().getClass();
        return Arrays.stream(clazz.getMethods())
                .filter(
                        x -> {
                            List<String> collect =
                                    Arrays.stream(x.getParameterTypes())
                                            .map(Class::getName)
                                            .map(String::toLowerCase)
                                            .collect(Collectors.toList());

                            return collect.contains(exception.getClass().getName().toLowerCase());
                        })
                .findFirst();
    }

    /**
     * Invoke fallback method.
     *
     * @param point
     * @param exception
     * @return
     */
    private static Object invokeFallback(
            ProceedingJoinPoint point, StatusRuntimeException exception) {

        try {
            Optional<Method> fallbackMethod = getFallbackMethod(point, exception);
            if (fallbackMethod.isPresent()) {
                Object[] args = point.getArgs();
                int oldSize = args.length;
                Object[] newArgs = new Object[oldSize + 1];
                System.arraycopy(args, 0, newArgs, 0, oldSize);
                newArgs[oldSize] = exception;

                return fallbackMethod.get().invoke(point.getTarget(), newArgs);
            }

            log.warn(
                    "Cann't find fallback class, and return null for method: {}",
                    point.getSignature().getName());
            return null;
        } catch (Exception ex) {
            log.info("Execute fallback method failed, cause by: ", ex);
        }

        return null;
    }

    @Pointcut("@annotation(fallbackAnno)")
    public void pointCut(FallbackAnno fallbackAnno) {}

    @Around("pointCut(fallbackAnno)")
    public Object doPoint(ProceedingJoinPoint point, FallbackAnno fallbackAnno) throws Throwable {

        try {
            return point.proceed();
        } catch (StatusRuntimeException exception) {
            // call interface fallback method
            return invokeFallback(point, exception);
        }
    }
}
