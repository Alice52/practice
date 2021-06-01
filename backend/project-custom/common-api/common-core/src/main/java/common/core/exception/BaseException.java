package common.core.exception;

import common.core.exception.assertion.IBaseErrorResponse;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2021-06-01<br>
 * @project project-custom <br>
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    @NotNull private IBaseErrorResponse responseEnum;

    @Nullable private Object[] args;

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
