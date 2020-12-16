package cn.edu.ntu.common.api.exception;

import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
public class BusinessException extends BaseException {

  private static final long serialVersionUID = 1L;

  public BusinessException(IBaseErrorResponse responseEnum, Object[] args, String message) {
    super(responseEnum, args, message);
  }

  public BusinessException(
      IBaseErrorResponse responseEnum, Object[] args, String message, Throwable cause) {
    super(responseEnum, args, message, cause);
  }
}
