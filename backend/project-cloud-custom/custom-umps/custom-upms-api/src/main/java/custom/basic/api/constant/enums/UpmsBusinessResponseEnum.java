package custom.basic.api.constant.enums;

import common.core.exception.assertion.IBusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UpmsBusinessResponseEnum implements IBusinessExceptionAssert {
    SIGN_ERROR(400_100_001, "簽到錯誤");

    private Integer errorCode;
    private String errorMsg;
}
