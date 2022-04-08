package custom.basic.api.dto;

import java.io.Serializable;

import custom.basic.api.constant.enums.ShortMessageTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author asd <br>
 * @create 2021-06-29 5:39 PM <br>
 * @project custom-upms-grpc <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CaptchaImageDTO implements Serializable {

    /** 图片验证码 */
    private String code;
    /** 手机号 */
    private String mobile;

    private String captchaKey;

    private ShortMessageTypeEnum messageType;
}
