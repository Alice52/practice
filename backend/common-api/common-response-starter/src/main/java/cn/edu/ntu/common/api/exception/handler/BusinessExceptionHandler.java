package cn.edu.ntu.common.api.exception.handler;

import cn.edu.ntu.common.api.exception.BaseException;
import cn.edu.ntu.common.api.response.model.ErrorResponse;
import cn.edu.ntu.common.api.service.UnifiedMessageSource;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BusinessExceptionHandler {
  @Resource private UnifiedMessageSource unifiedMessageSource;

  @ExceptionHandler(value = {BaseException.class})
  @ResponseBody
  public ErrorResponse handleBusinessException(BaseException e) {
    log.error(e.getMessage(), e);

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
