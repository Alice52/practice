package custom.basic.api.dto;

import java.io.Serializable;
import java.util.List;

import custom.basic.api.entity.SocialAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author asd <br>
 * @create 2021-06-30 9:21 AM <br>
 * @project custom-upms-grpc <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSocialDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /** 主键ID */
    private Long memberId;

    private String nickname;
    private String phoneNumber;
    private List<SocialAccount> socialAccounts;
    private Object basicInfo;
}
