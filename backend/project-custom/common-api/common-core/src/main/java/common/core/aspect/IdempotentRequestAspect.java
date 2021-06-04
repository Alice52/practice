package common.core.aspect;

import cn.hutool.core.util.StrUtil;
import common.core.annotation.LocalIdempotentRequest;
import common.core.util.RequestUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zack <br>
 * @create 2021-04-12 17:47 <br>
 * @project common-core <br>
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class IdempotentRequestAspect {

    /** <uri, <token, count>> */
    private static final Map<String, ExpiringMap<String, Integer>> map =
            new ConcurrentHashMap<>(16);

    /**
     * 切入去点拦截
     *
     * @see LocalIdempotentRequest
     */
    @Pointcut("@annotation(localIdempotentRequest)")
    public void pointCut(LocalIdempotentRequest localIdempotentRequest) {}

    @Around("pointCut(localIdempotentRequest)")
    public Object doPoint(ProceedingJoinPoint point, LocalIdempotentRequest idempotentRequest)
            throws Throwable {

        HttpServletRequest request = RequestUtil.getCurrentRequest();
        String token = RequestUtil.getCurrentToken();
        // if token is null, will not do any limit.
        if (StrUtil.isEmpty(token)) {
            return point.proceed();
        }

        ExpiringMap<String, Integer> em =
                map.getOrDefault(
                        request.getRequestURI(),
                        ExpiringMap.builder().variableExpiration().build());
        Integer count = em.getOrDefault(token, 0);

        // 超过次数，不执行目标方法
        // 可以直接返回, 也可以抛异常
        if (count >= 1) {
            return null;
        }

        em.put(
                token,
                1,
                ExpirationPolicy.CREATED,
                idempotentRequest.time(),
                idempotentRequest.timeUnit());
        map.put(request.getRequestURI(), em);

        return point.proceed();
    }
}
