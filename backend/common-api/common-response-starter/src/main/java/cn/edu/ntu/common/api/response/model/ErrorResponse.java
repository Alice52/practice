package cn.edu.ntu.common.api.response.model;

import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements IBaseErrorResponse {
  private Integer errorCode;
  private String errorMsg;
  private Map<String, Object> parameters;

  public ErrorResponse(IBaseErrorResponse responseEnum) {
    this(responseEnum.getErrorCode(), responseEnum.getErrorMsg(), responseEnum.getParameters());
  }

  public ErrorResponse(IBaseErrorResponse responseEnum, Map<String, Object> parameters) {
    this(responseEnum.getErrorCode(), responseEnum.getErrorMsg(), parameters);
  }

  public ErrorResponse(Integer code, String message) {
    this.errorCode = code;
    this.errorMsg = message;
  }
}
