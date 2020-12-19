package cn.edu.ntu.common.api.exception.handler;

import cn.edu.ntu.common.api.constants.enums.CommonResponseEnum;
import cn.edu.ntu.common.api.constants.enums.ServletResponseEnum;
import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;
import cn.edu.ntu.common.api.response.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Order
@ControllerAdvice
@ResponseBody
@ConditionalOnProperty(
    prefix = "common.response.handler",
    value = {"enabled"},
    havingValue = "true",
    matchIfMissing = true)
public class ServletExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHandler.class);

  @ExceptionHandler({
    NoHandlerFoundException.class,
    HttpRequestMethodNotSupportedException.class,
    HttpMediaTypeNotSupportedException.class,
    HttpMediaTypeNotAcceptableException.class,
    MissingPathVariableException.class,
    MissingServletRequestParameterException.class,
    TypeMismatchException.class,
    HttpMessageNotReadableException.class,
    HttpMessageNotWritableException.class,
    // BindException.class,
    // MethodArgumentNotValidException.class
    ServletRequestBindingException.class,
    ConversionNotSupportedException.class,
    MissingServletRequestPartException.class,
    AsyncRequestTimeoutException.class
  })
  public IBaseErrorResponse handleException(Exception e) {
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
