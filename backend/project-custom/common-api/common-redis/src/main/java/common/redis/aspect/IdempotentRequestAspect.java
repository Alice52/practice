package common.redis.aspect;

import cn.hutool.core.util.StrUtil;
import common.core.util.UserUtil;
import common.core.util.WebUtil;
import common.redis.annotation.RedisIdempotentRequest;
import common.redis.constants.enums.RedisKeyCommonEnum;
import common.redis.utils.RedisUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br>
 * @create 2021-06-04 16:22 <br>
 * @project custom-test <br>
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
public class IdempotentRequestAspect {

    @Resource private RedisUtil redisUtil;

    /**
     * 切入去点拦截
     *
     * @see RedisIdempotentRequest
     */
    @Pointcut("@annotation(redisIdempotentRequest)")
    public void pointCut(RedisIdempotentRequest redisIdempotentRequest) {}

    @Around("pointCut(redisIdempotentRequest)")
    public Object doPoint(ProceedingJoinPoint point, RedisIdempotentRequest redisIdempotentRequest)
            throws Throwable {

        HttpServletRequest request = WebUtil.getCurrentRequest();
        String token = WebUtil.getCurrentToken();
        // if token is null, will not do any limit.
        if (StrUtil.isEmpty(token)) {
            return point.proceed();
        }

        long currentRequestCount =
                redisUtil.increment(
                        RedisKeyCommonEnum.CACHE_LIMIT,
                        1,
                        redisIdempotentRequest.time(),
                        redisIdempotentRequest.timeUnit(),
                        UserUtil.getCurrentMemberId(),
                        request.getRequestURI());

        if (currentRequestCount == 1) {
            return point.proceed();
        }

        // 超过次数，不执行目标方法
        log.error(
                "接口请求太过频繁, PATH: {}, IP: {}", request.getRequestURI(), WebUtil.getIpAddr(request));
        return null;
    }
}
