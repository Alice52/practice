package custom.upms.grpc.configuration;

import common.cloud.grpc.interceptor.LogInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Configuration;

/**
 * @author asd <br>
 * @create 2021-06-28 3:30 PM <br>
 * @project common-core <br>
 */
@Configuration(proxyBeanMethods = false)
public class GrpcConfiguration {

    /**
     * Creates a new {@link LogInterceptor} bean and adds it to the global interceptor list. As an
     * alternative you can directly annotate the {@code LogGrpcInterceptor} class and it will
     * automatically be picked up by spring's classpath scanning.
     *
     * @return The newly created bean.
     */
    @GrpcGlobalClientInterceptor
    public LogInterceptor clientLogClientInterceptor() {
        return new LogInterceptor();
    }

    @GrpcGlobalServerInterceptor
    public LogInterceptor serverLogClientInterceptor() {
        return new LogInterceptor();
    }
}
