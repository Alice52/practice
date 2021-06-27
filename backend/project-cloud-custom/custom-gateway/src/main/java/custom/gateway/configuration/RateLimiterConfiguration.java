package custom.gateway.configuration;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @author zack <br/>
 * @create 2021-06-26<br/>
 * @project project-cloud-custom <br/>
 */
@Configuration
public class RateLimiterConfiguration {
    @Bean(value = "remoteAddressKeyResolver")
    public KeyResolver remoteAddressKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
