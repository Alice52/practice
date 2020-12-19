package cn.edu.ntu.common.api.utils;

import cn.edu.ntu.common.api.constants.enums.CommonResponseEnum;
import cn.edu.ntu.common.api.constants.enums.ServletResponseEnum;
import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;
import cn.edu.ntu.common.api.response.model.ErrorResponse;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author zack <br>
 * @create 2020/12/19 <br>
 * @project common-api <br>
 */
public final class RequestUtil {

  public static String getRequestId(HttpServletRequest request, String requestIdKey) {
    String requestId;
    String parameterRequestId = request.getParameter(requestIdKey);
    String headerRequestId = request.getHeader(requestIdKey);
    if (parameterRequestId == null && headerRequestId == null) {
      requestId = UUID.randomUUID().toString();
    } else {
      requestId = parameterRequestId != null ? parameterRequestId : headerRequestId;
    }
    return requestId;
  }

  public static IBaseErrorResponse handleServletException(Logger LOGGER, Exception e) {
    LOGGER.error(e.getMessage(), e);

    int code = CommonResponseEnum.SERVER_ERROR.getErrorCode();
    try {
      ServletResponseEnum servletExceptionEnum =
          ServletResponseEnum.valueOf(e.getClass().getSimpleName());
      code = servletExceptionEnum.getCode();
    } catch (IllegalArgumentException e1) {
      LOGGER.error(
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

    return new ErrorResponse(code, e.getMessage());
  }
}
