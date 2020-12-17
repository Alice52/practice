package cn.edu.ntu.common.api.constants.enums;

import cn.edu.ntu.common.api.exception.assertion.IBaseExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Getter
@AllArgsConstructor
public enum CommonResponseEnum implements IBaseExceptionAssert {
  CUSTOM(-1, "Internal Error"),
  SUCCESS(0, "SUCCESS"),
  SERVER_ERROR(99999, "Internal Error"),
  SERVER_BUSY(99998, "Network Error"),
  BEAN_VALIDATION(40001, "Invalid Parameter"),

  // Time
  DATE_NOT_NULL(50001, "Date Is Null"),
  DATETIME_NOT_NULL(50001, "DateTime Is Null"),
  TIME_NOT_NULL(50001, "Time Is Null"),
  DATE_PATTERN_MISMATCH(5002, "Date[%s] Cannot Convert To Date[%s]"),
  PATTERN_NOT_NULL(5003, "Pattern Is Null"),
  PATTERN_INVALID(5003, "Arg[%s] Is Not Correct DateTime");

  private Integer errorCode;
  private String errorMsg;
  private Map<String, Object> parameters;

  CommonResponseEnum(Integer errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  @Override
  public void setParameters(Map<String, Object> parameters) {
    this.parameters = parameters;
  }
}
