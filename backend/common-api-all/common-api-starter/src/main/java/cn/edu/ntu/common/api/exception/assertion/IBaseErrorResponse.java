package cn.edu.ntu.common.api.exception.assertion;

import java.util.Map;

/**
 * If Rest-API is failed, then create IErrorResponse.
 *
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
public interface IBaseErrorResponse {
  /**
   * Error Code. And this value cannot changed anyway.
   *
   * @return
   */
  Integer getErrorCode();

  /**
   * Error message.
   *
   * @return
   */
  String getErrorMsg();

  /**
   * Set Error message.
   *
   * @param errorMsg
   */
  default void setErrorMsg(String errorMsg) {}

  /**
   * Error parameters.
   *
   * @return
   */
  Map<String, Object> getParameters();

  /**
   * Set Error parameters details.
   *
   * @param parameters
   */
  default void setParameters(Map<String, Object> parameters) {}
}
