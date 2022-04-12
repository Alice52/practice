package custom.basic.api.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author asd <br>
 * @create 2021-06-29 5:45 PM <br>
 * @project custom-upms-grpc <br>
 */
@Data
public class SmsMemberVO implements Serializable {

    private Long memberId;
    private String mobile;
}
