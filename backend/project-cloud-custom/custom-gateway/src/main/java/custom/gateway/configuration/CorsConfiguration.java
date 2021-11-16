package custom.gateway.configuration;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zack <br>
 * @create 2020-10-08 20:59 <br>
 * @project project-ec <br>
 */
@Configuration
public class CorsConfiguration {
    @Value("${allow-cors-domains}")
    private String[] allowDomains;

    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            Set<String> allowedOrigins = new HashSet(Arrays.asList(allowDomains));
            String originHeader = request.getHeaders().getOrigin();
            if (allowedOrigins.contains(originHeader)
                    || (allowedOrigins.contains("*") && StringUtils.isNotBlank(originHeader))) {
                HttpHeaders requestHeaders = request.getHeaders();
                ServerHttpResponse response = ctx.getResponse();
                HttpHeaders headers = response.getHeaders();
                headers.add("Access-Control-Allow-Origin", requestHeaders.getOrigin());
                headers.add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
                headers.add("Access-Control-Max-Age", "18000L");
                headers.add(
                        "Access-Control-Allow-Headers",
                        "istoken,Authorization,Content-Type,Accept,Origin,User-Agent,DNT,Cache-Control,X-Mx-ReqToken,X-Requested-With");
                headers.add("Access-Control-Expose-Headers", "*");
                headers.add("Access-Control-Allow-Credentials", "true");
                if (request.getMethod() == HttpMethod.OPTIONS) {
                    response.setStatusCode(HttpStatus.OK);
                    return Mono.empty();
                }
            }
            return chain.filter(ctx);
        };
    }
}
