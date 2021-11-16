package custom.basic.api.dto;

import custom.basic.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @author asd <br>
 * @create 2021-06-30 8:56 AM <br>
 * @project custom-upms-grpc <br>
 */
@Data
public class UserDTO implements Serializable {
    /** 用户基本信息 */
    private SysUser sysUser;

    /** 权限标识集合 */
    private String[] permissions;

    /** 角色集合 */
    private Integer[] roles;
}
