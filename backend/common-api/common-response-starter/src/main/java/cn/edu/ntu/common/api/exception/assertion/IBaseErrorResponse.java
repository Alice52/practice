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
   * Error Code.
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
