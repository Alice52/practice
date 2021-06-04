package common.core.util;

import common.core.configuration.SnowflakeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br>
 * @create 2021-06-01<br>
 * @project project-custom <br>
 */
@Slf4j
@Component
public class RequestUtil {
    @Resource private SnowflakeConfig snowflake;
    private static final String BEARER_ = "Bearer ";

    public String getRequestId(HttpServletRequest request, String requestIdKey) {
        String requestId;
        String parameterRequestId = request.getParameter(requestIdKey);
        String headerRequestId = request.getHeader(requestIdKey);
        if (parameterRequestId == null && headerRequestId == null) {
            requestId = String.valueOf(snowflake.snowflakeId());
        } else {
            requestId = parameterRequestId != null ? parameterRequestId : headerRequestId;
        }
        return requestId;
    }

    public static HttpServletRequest getCurrentRequest() {
        // 获得request对象
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        return sra.getRequest();
    }

    @Nullable
    public static String getCurrentToken() {
        HttpServletRequest request = getCurrentRequest();
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(BEARER_)) {
            return null;
        }

        return header.replace(BEARER_, "");
    }

    /**
     * Gets the IP address of the login user's remote host
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
