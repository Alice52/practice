package custom.basic.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import common.api.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author asd <br>
 * @create 2021-06-30 9:22 AM <br>
 * @project custom-upms-grpc <br>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_account")
public class SocialAccount extends BaseEntity<SocialAccount> {
    @TableId private Long id;
    /** */
    private Long memberId;
    /** 0=微信 */
    private Integer socialType;
    /** */
    private String openId;
    /** */
    private String unionId;

    private String clientId;
    /** */
    private String nickname;

    private String location;

    private Integer gender;
    /** 头像 */
    private String avatar;
}
