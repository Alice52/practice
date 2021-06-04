package common.redis.aspect;

import cn.hutool.core.util.NumberUtil;
import com.google.common.util.concurrent.RateLimiter;
import common.core.annotation.LocalLimitRequest;
import common.core.constant.enums.CommonResponseEnum;
import common.core.exception.BaseException;
import common.core.util.RequestUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * @create 2021-04-12 14:47 <br>
 * @project common-core <br>
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class LimitRequestAspect {

    /** 根据请求地址保存不同的令牌桶 */
    private static final Map<String, RateLimiter> map = new ConcurrentHashMap<>(16);

    /**
     * 切入去点拦截
     *
     * @see LocalLimitRequest
     */
    @Pointcut("@annotation(localLimitRequest)")
    public void pointCut(LocalLimitRequest localLimitRequest) {}

    @Around("pointCut(localLimitRequest)")
    public Object doPoint(ProceedingJoinPoint joinPoint, LocalLimitRequest limitRequest)
            throws Throwable {

        if (limitRequest.count() == 0 || limitRequest.time() == 0) {
            return joinPoint.proceed();
        }

        // 获取 request
        HttpServletRequest request = RequestUtil.getCurrentRequest();
        // 获取请求 uri
        String uri = request.getRequestURI();
        if (!map.containsKey(uri)) {
            // 为当前请求创建令牌桶
            map.put(
                    uri,
                    RateLimiter.create(NumberUtil.div(limitRequest.count(), limitRequest.time())));
        }
        // 根据请求 uri 获取令牌桶
        RateLimiter rateLimiter = map.get(uri);

        boolean acquire =
                rateLimiter.tryAcquire(
                        limitRequest.acquireTokenTimeout(), limitRequest.acquireTokenTimeUnit());
        if (acquire) {
            // 调用目标方法
            return joinPoint.proceed();
        }

        // 获取不到令牌抛出异常
        throw new BaseException(CommonResponseEnum.REQUEST_LIMIT_ERROR);
    }
}
