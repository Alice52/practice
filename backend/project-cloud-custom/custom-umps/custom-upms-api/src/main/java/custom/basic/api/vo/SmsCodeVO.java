package custom.basic.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author asd <br>
 * @create 2021-06-29 5:39 PM <br>
 * @project custom-upms-grpc <br>
 */
@Data
public class SmsCodeVO implements Serializable {
    private String mobile;
    private String code;
    private Long countdown;
}
