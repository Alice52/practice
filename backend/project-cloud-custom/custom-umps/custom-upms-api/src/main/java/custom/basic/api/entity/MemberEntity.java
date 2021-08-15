package custom.basic.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import common.api.model.BaseEntity;
import lombok.Data;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@Data
@TableName("upms_member")
public class MemberEntity extends BaseEntity<MemberEntity> {
    private static final long serialVersionUID = 1L;

    @TableId(type = AUTO)
    private Long id;

    private String username;
    private String nickname;
    private String phone;
    private String email;
    private String password;
    private String avatarUrl;
    private Integer isActived;
    @TableLogic private Boolean isDeleted;
}
