package cn.edu.ntu.common.api.exception;

/**
 * 只包装了 错误信息 的 {@link RuntimeException}. 用于 {@link
 * cn.edu.ntu.common.api.exception.assertion.IBaseAssert} 中用于包装自定义异常信息
 *
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
public class WrapMessageException extends RuntimeException {

  public WrapMessageException(String message) {
    super(message);
  }

  public WrapMessageException(String message, Throwable cause) {
    super(message, cause);
  }
}
