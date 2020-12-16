package cn.edu.ntu.common.api.exception.assertion;

import cn.edu.ntu.common.api.exception.BaseException;
import cn.edu.ntu.common.api.exception.BusinessException;

import java.text.MessageFormat;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
public interface IBusinessExceptionAssert extends IBaseErrorResponse, IBaseAssert {

  @Override
  default BaseException newException(Object... args) {
    String msg = this.getErrorMsg();
    if (args != null && args.length > 0) {
      msg = MessageFormat.format(this.getErrorMsg(), args);
    }

    return new BusinessException(this, args, msg);
  }

  @Override
  default BaseException newException(Throwable t, Object... args) {
    String msg = this.getErrorMsg();
    if (args != null && args.length > 0) {
      msg = MessageFormat.format(this.getErrorMsg(), args);
    }

    return new BusinessException(this, args, msg, t);
  }
}
