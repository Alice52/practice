package custom.basic.api.constant.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author asd <br>
 * @create 2021-06-30 9:38 AM <br>
 * @project custom-upms-grpc <br>
 */
@AllArgsConstructor
@Getter
public enum SocialType {
    WECHAT_MINI_APP(0),
    OFFICIAL_ACCOUNT(1),
    WECHAT_WORK(2),
    WX(3),
    ;

    @EnumValue private final int code;
}
