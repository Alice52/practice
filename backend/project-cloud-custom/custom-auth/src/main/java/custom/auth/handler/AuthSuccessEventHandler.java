package custom.auth.handler;

import common.security.listener.AbstractAuthSuccessEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;

/**
 * @author asd <br>
 * @create 2021-06-30 5:06 PM <br>
 * @project custom-upms-grpc <br>
 */
@Slf4j
public class AuthSuccessEventHandler extends AbstractAuthSuccessEventHandler {

    /**
     * 处理登录成功方法
     *
     * <p>获取到登录的authentication 对象
     *
     * @param authentication 登录对象
     */
    @Override
    public void handle(Authentication authentication) {
        log.info("用户：{} 登录成功", authentication.getPrincipal());
    }
}
