package common.core.util;

import common.core.constant.CommonConstants;
import common.core.exception.BaseException;
import common.core.exception.assertion.IBaseErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author zack <br>
 * @create 2021-06-01 18:29 <br>
 * @project custom-test <br>
 */
@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@Data
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code = CommonConstants.SUCCESS;
    private String msg = "success";
    private T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = CommonConstants.FAIL;
    }

    public R(BaseException e) {
        super();
        this.msg = e.getMessage();
        this.code = e.getResponseEnum().getErrorCode();
    }

    public R(IBaseErrorResponse response) {
        super();
        this.msg = response.getErrorMsg();
        this.code = response.getErrorCode();
    }

    @NotNull
    public static <T> R error(@Nullable IBaseErrorResponse errorResponse) {

        return R.builder()
                .msg(errorResponse.getErrorMsg())
                .code(errorResponse.getErrorCode())
                .build();
    }

    @NotNull
    public static <T> R error(@Nullable IBaseErrorResponse errorResponse, @Nullable T data) {

        return R.builder()
                .data(data)
                .msg(errorResponse.getErrorMsg())
                .code(errorResponse.getErrorCode())
                .build();
    }

    @NotNull
    public static <T> R success(@Nullable T data) {

        return R.builder().data(data).build();
    }
}
