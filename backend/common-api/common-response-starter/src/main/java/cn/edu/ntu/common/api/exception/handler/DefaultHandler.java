package cn.edu.ntu.common.api.exception.handler;

import cn.edu.ntu.common.api.constants.enums.CommonResponseEnum;
import cn.edu.ntu.common.api.response.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DefaultHandler {

  @ExceptionHandler({Exception.class})
  public ErrorResponse handleException(Exception ex) {
    log.error(ex.getMessage(), ex);

    return new ErrorResponse(CommonResponseEnum.SERVER_ERROR.getErrorCode(), ex.getMessage());
  }
}
