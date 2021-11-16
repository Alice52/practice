package top.hubby.exception.static$;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-28 2:56 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Getter
public class BusinessException extends RuntimeException {

    private int code;

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }
}
