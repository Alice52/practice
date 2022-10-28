package custom.basic.api.feign.factory;

import custom.basic.api.feign.RemoteSmsCodeService;
import custom.basic.api.feign.fallback.RemoteSmsCodeServiceFallbackImpl;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author asd <br>
 * @create 2021-06-29 5:37 PM <br>
 * @project custom-upms-grpc <br>
 */
@Component
public class RemoteSmsCodeServiceFallbackFactory implements FallbackFactory<RemoteSmsCodeService> {

    @Override
    public RemoteSmsCodeService create(Throwable throwable) {
        RemoteSmsCodeServiceFallbackImpl remoteSmsCodeServiceFallback =
                new RemoteSmsCodeServiceFallbackImpl();
        remoteSmsCodeServiceFallback.setCause(throwable);
        return remoteSmsCodeServiceFallback;
    }
}
