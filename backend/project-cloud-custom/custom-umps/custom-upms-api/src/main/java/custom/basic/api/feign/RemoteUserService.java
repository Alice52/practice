package custom.basic.api.feign;

import common.core.constant.SecurityConstants;
import common.core.constant.ServiceNameConstants;
import common.core.util.R;
import custom.basic.api.dto.UserDTO;
import custom.basic.api.feign.factory.RemoteUserServiceFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author asd <br>
 * @create 2021-06-30 8:55 AM <br>
 * @project custom-upms-grpc <br>
 */
@FeignClient(
        value = ServiceNameConstants.UMPS_SERVICE,
        fallbackFactory = RemoteUserServiceFallbackFactory.class)
public interface RemoteUserService {
    /**
     * Get user info: /inner/user?username=xx
     *
     * @param username 用户名
     * @param from 调用标志
     * @return R
     */
    @GetMapping("/inner/user")
    R<UserDTO> getUser(
            @RequestParam("username") String username,
            @RequestHeader(SecurityConstants.FROM) String from);
}
