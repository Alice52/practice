package custom.basic.api.feign.fallback;

import common.core.util.R;
import custom.basic.api.dto.UserDTO;
import custom.basic.api.feign.RemoteUserService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * @author asd <br>
 * @create 2021-06-30 9:16 AM <br>
 * @project custom-upms-grpc <br>
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {
    @Setter private Throwable cause;

    /**
     * 通过用户名查询用户、角色信息
     *
     * @param username 用户名
     * @param from 内外标志
     * @return R
     */
    @Override
    public R<UserDTO> getUser(String username, String from) {
        log.error("feign 查询用户信息失败:{}", username, cause);
        return null;
    }
}
