package custom.basic.api.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import common.api.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author asd <br>
 * @create 2021-06-30 8:57 AM <br>
 * @project custom-upms-grpc <br>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("system_user")
public class SysUser extends BaseEntity<SocialAccount> {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /** 用户名 */
    private String username;

    /** 系统用户类型 */
    private Integer userType;

    /** 昵称 */
    private String nickname;

    private String password;

    /** 创建时间 */
    private LocalDateTime createTime;
    /** 修改时间 */
    private LocalDateTime updateTime;
    /** 0-正常，1-删除 */
    @TableLogic private String delFlag;

    /** 锁定标记 */
    private String lockFlag;

    /** 简介 */
    private String phone;
    /** 头像 */
    private String avatar;

    /** 部门ID */
    private Integer deptId;
}
