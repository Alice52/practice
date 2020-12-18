package cn.edu.ntu.common.api.exception.handler;

import cn.edu.ntu.common.api.exception.BaseException;
import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;
import cn.edu.ntu.common.api.response.model.ErrorResponse;
import cn.edu.ntu.common.api.service.UnifiedMessageSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2020/12/17 <br>
 * @project common-api <br>
 */
@Order(999)
@ControllerAdvice
@ResponseBody
@ConditionalOnProperty(
    prefix = "common.response.handler",
    value = {"enabled"},
    havingValue = "true",
    matchIfMissing = true)
public class BusinessExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(BusinessExceptionHandler.class);
  @Resource private UnifiedMessageSource unifiedMessageSource;

  @ExceptionHandler(value = {BaseException.class})
  @ResponseBody
  public IBaseErrorResponse handleBusinessException(BaseException e) {
    LOGGER.error(e.getMessage(), e);

    return new ErrorResponse(e.getResponseEnum().getErrorCode(), getMessage(e));
  }

  /**
   * Get i18 message.
   *
   * @param e
   * @return
   */
  public String getMessage(BaseException e) {
    String code = "response." + e.getResponseEnum().toString();
    String message = unifiedMessageSource.getMessage(code, e.getArgs());

    if (message == null || message.isEmpty()) {
      return e.getMessage();
    }

    return message;
  }
}
