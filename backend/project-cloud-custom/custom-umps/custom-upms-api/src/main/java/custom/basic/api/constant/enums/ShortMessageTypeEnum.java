package custom.basic.api.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author asd <br>
 * @create 2021-06-29 5:40 PM <br>
 * @project custom-upms-grpc <br>
 */
@AllArgsConstructor
@Getter
public enum ShortMessageTypeEnum {
    LOGIN("login"),
    ACCOUNT_VERIFY("account_verify"),
    CHANGE_MOBILE("change_mobile");

    private String type;
}
