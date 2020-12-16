package cn.edu.ntu.common.api.exception.assertion;

import cn.edu.ntu.common.api.exception.BaseException;

import java.text.MessageFormat;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
public interface IBaseExceptionAssert extends IBaseErrorResponse, IBaseAssert {

  /**
   * Create BaseException by args[caller].
   *
   * @param args
   * @return
   */
  @Override
  default BaseException newException(Object... args) {
    String msg = MessageFormat.format(this.getErrorMsg(), args);

    return new BaseException(this, args, msg);
  }

  /**
   * Create BaseException by args[caller] and Throwable.
   *
   * @param t
   * @param args
   * @return
   */
  @Override
  default BaseException newException(Throwable t, Object... args) {
    String msg = MessageFormat.format(this.getErrorMsg(), args);

    return new BaseException(this, args, msg, t);
  }
}
