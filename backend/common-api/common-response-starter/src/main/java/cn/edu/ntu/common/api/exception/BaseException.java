package cn.edu.ntu.common.api.exception;

import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
public class BaseException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  @NotNull private IBaseErrorResponse responseEnum;
  /** Notice this property maybe null. */
  private Object[] args;

  public BaseException() {}

  public BaseException(IBaseErrorResponse responseEnum) {
    super(responseEnum.getErrorMsg());
    this.responseEnum = responseEnum;
  }

  public BaseException(int code, String msg) {
    super(msg);
    this.responseEnum =
        new IBaseErrorResponse() {
          @Override
          public Integer getErrorCode() {
            return code;
          }

          @Override
          public String getErrorMsg() {
            return msg;
          }

          @Override
          public Map<String, Object> getParameters() {
            return new HashMap<>(1);
          }
        };
  }

  public BaseException(IBaseErrorResponse responseEnum, Object[] args, String message) {
    super(message);
    this.responseEnum = responseEnum;
    this.args = args;
  }

  public BaseException(
      IBaseErrorResponse responseEnum, Object[] args, String message, Throwable cause) {
    super(message, cause);
    this.responseEnum = responseEnum;
    this.args = args;
  }

  public IBaseErrorResponse getResponseEnum() {
    return responseEnum;
  }

  public void setResponseEnum(IBaseErrorResponse responseEnum) {
    this.responseEnum = responseEnum;
  }

  public Object[] getArgs() {
    return args;
  }

  public void setArgs(Object[] args) {
    this.args = args;
  }
}
