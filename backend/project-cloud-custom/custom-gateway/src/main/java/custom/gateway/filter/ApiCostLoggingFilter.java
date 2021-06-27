package custom.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zack <br>
 * @create 2021-06-26<br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Component
public class ApiCostLoggingFilter implements GlobalFilter, Ordered {

    private static final String START_TIME = "startTime";

    public ApiCostLoggingFilter() {
        log.info("Loaded ApiCostLoggingFilter");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange)
                .then(
                        Mono.fromRunnable(
                                () -> {
                                    try {
                                        Long startTime = exchange.getAttribute(START_TIME);
                                        if (startTime != null) {
                                            log.info(
                                                    "API execution time: Method={%s} Path={%s} executeTime={%s}",
                                                    exchange.getRequest().getMethod().name(),
                                                    exchange.getRequest().getURI().getPath(),
                                                    System.currentTimeMillis() - startTime);
                                        }
                                    } catch (Exception e) {
                                        log.warn("Log API execution time failed");
                                    }
                                }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
