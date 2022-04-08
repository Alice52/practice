package custom.basic.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author asd <br>
 * @create 2021-06-29 5:44 PM <br>
 * @project custom-upms-grpc <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmsCodeDTO implements Serializable {

    private String mobile;

    private String smsCode;

    private String smsKey;

    private String smsValue;

    @Deprecated @JsonIgnore private String clientId;
}
