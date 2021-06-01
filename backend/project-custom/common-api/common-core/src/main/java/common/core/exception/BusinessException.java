package common.core.exception;

/**
 * @author zack <br>
 * @create 2021-06-01 18:31 <br>
 * @project custom-test <br>
 */
public class BusinessException extends RuntimeException {

    private int code = 1;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
