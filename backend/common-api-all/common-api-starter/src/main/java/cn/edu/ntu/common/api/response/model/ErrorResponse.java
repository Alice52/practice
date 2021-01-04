package cn.edu.ntu.common.api.response.model;

import cn.edu.ntu.common.api.exception.assertion.IBaseErrorResponse;

import java.util.Map;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
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

  public ErrorResponse() {}

  public ErrorResponse(Integer errorCode, String errorMsg, Map<String, Object> parameters) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
    this.parameters = parameters;
  }

  @Override
  public Integer getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(Integer errorCode) {
    this.errorCode = errorCode;
  }

  @Override
  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  @Override
  public Map<String, Object> getParameters() {
    return parameters;
  }

  @Override
  public void setParameters(Map<String, Object> parameters) {
    this.parameters = parameters;
  }
}
