package common.core.exception.handler;

import common.core.exception.BusinessException;
import common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zack <br>
 * @create 2021-06-01<br>
 * @project project-custom <br>
 */
@Order(999)
@RestControllerAdvice
@ConditionalOnProperty(
        prefix = "common.core.global.handler",
        value = {"enabled"},
        havingValue = "true",
        matchIfMissing = true)
@Slf4j
public class BusinessExceptionHandler {

    @ExceptionHandler(value = {BusinessException.class})
    public R handleBusinessException(BusinessException ex) {
        log.error(ex.getMessage(), ex);

        return R.error(ex.getResponseEnum(), ex);
    }
}
