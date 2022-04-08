package custom.auth.handler;

import common.security.listener.AbstractAuthFailureEventHandler;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author asd <br>
 * @create 2021-06-30 5:05 PM <br>
 * @project custom-upms-grpc <br>
 */
@Slf4j
public class AuthFailureEventHandler extends AbstractAuthFailureEventHandler {

    /**
     * 处理登录失败方法
     *
     * <p>
     *
     * @param authenticationException 登录的authentication 对象
     * @param authentication 登录的authenticationException 对象
     */
    @Override
    public void handle(
            AuthenticationException authenticationException, Authentication authentication) {
        log.info(
                "用户：{} 登录失败，异常：{}",
                authentication.getPrincipal(),
                authenticationException.getLocalizedMessage());
    }
}
