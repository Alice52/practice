package common.api.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@Data
@Deprecated
@EqualsAndHashCode(callSuper = false)
public class CloudException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public CloudException() {}

    public CloudException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CloudException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CloudException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public CloudException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
