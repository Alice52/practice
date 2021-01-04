package cn.edu.ntu.common.api.exception.handler;

import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;
import cn.edu.ntu.common.api.utils.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Order(100)
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
  public IBaseErrorResponse handleException(HttpServletRequest request, Exception e) {
    return RequestUtil.handleServletException(LOGGER, e);
  }
}
