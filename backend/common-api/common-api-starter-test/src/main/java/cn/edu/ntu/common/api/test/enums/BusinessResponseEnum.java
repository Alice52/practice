package cn.edu.ntu.common.api.test.enums;

import cn.edu.ntu.common.api.exception.assertion.IBusinessExceptionAssert;
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
public enum BusinessResponseEnum implements IBusinessExceptionAssert {
  BAD_LICENCE_TYPE(7001, "Bad licence type."),
  LICENCE_NOT_FOUND(7002, "Licence not found.");

  private Integer errorCode;
  private String errorMsg;
  private Map<String, Object> parameters;
  BusinessResponseEnum(Integer errorCode, String errorMsg) {
    this.errorCode = errorCode;
    this.errorMsg = errorMsg;
  }

  @Override
  public void setParameters(Map<String, Object> parameters) {
    this.parameters = parameters;
  }
}
