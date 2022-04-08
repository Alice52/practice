package custom.basic.api.feign.factory;

import custom.basic.api.feign.RemoteUserService;
import custom.basic.api.feign.fallback.RemoteUserServiceFallbackImpl;
import feign.hystrix.FallbackFactory;

import org.springframework.stereotype.Component;

/**
 * @author asd <br>
 * @create 2021-06-30 9:15 AM <br>
 * @project custom-upms-grpc <br>
 */
@Component
public class RemoteUserServiceFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable throwable) {
        RemoteUserServiceFallbackImpl remoteUserServiceFallback =
                new RemoteUserServiceFallbackImpl();
        remoteUserServiceFallback.setCause(throwable);
        return remoteUserServiceFallback;
    }
}
