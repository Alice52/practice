package common.core.util;

import common.core.configuration.SnowflakeConfig;
import common.core.constant.enums.CommonResponseEnum;
import common.core.constant.enums.ServletResponseEnum;
import common.core.exception.assertion.IBaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    public static R handleServletException(Exception e) {
        log.error(e.getMessage(), e);

        IBaseErrorResponse response = CommonResponseEnum.INTERNAL_ERROR;
        try {
            response = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
        } catch (IllegalArgumentException e1) {
            log.error(
                    "class [{}] not defined in enum {}",
                    e.getClass().getName(),
                    ServletResponseEnum.class.getName());
        }

        // if (ENV_PROD.equals(profile)) {
        //     // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如404.
        //     code = CommonResponseEnum.SERVER_ERROR.getCode();
        //     BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
        //     String message = getMessage(baseException);
        //     return new ErrorResponse(code, message);
        // }
        return R.error(response, e);
    }

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
}
