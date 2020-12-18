package cn.edu.ntu.common.api.exception.handler;

import cn.edu.ntu.common.api.constants.enums.CommonResponseEnum;
import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;
import cn.edu.ntu.common.api.response.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class DefaultHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(DefaultHandler.class);

  @ExceptionHandler({Exception.class})
  public IBaseErrorResponse handleException(Exception ex) {
    LOGGER.error(ex.getMessage(), ex);

    return new ErrorResponse(CommonResponseEnum.SERVER_ERROR.getErrorCode(), ex.getMessage());
  }
}
