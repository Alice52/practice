package common.core.constant.enums;

import common.core.exception.assertion.IBaseExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2021-06-01<br>
 * @project project-custom <br>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CommonResponseEnum implements IBaseExceptionAssert {
    INTERNAL_ERROR(500_000_000, "Internal Error"),
    SERVER_BUSY(500_000_001, "Network Error"),
    BEAN_VALIDATION(400_000_000, "Invalid Parameter"),
    ;

    private Integer errorCode;
    private String errorMsg;
}
