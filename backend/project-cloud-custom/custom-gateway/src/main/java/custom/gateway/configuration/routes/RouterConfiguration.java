package custom.gateway.configuration.routes;

import custom.gateway.configuration.swagger.SwaggerResourceHandler;
import custom.gateway.configuration.swagger.SwaggerSecurityHandler;
import custom.gateway.configuration.swagger.SwaggerUiHandler;
import custom.gateway.handler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2021-06-26<br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Configuration
public class RouterConfiguration {
    @Resource private HystrixFallbackHandler hystrixFallbackHandler;
    @Resource private CaptchaHandler captchaHandler;
    @Resource private SwaggerResourceHandler swaggerResourceHandler;
    @Resource private SwaggerSecurityHandler swaggerSecurityHandler;
    @Resource private SwaggerUiHandler swaggerUiHandler;

    @Bean
    public RouterFunction captchaFunction() {
        return RouterFunctions.route(RequestPredicates.GET("/code"), captchaHandler);
    }

    @Bean
    public RouterFunction hystrixFallbackFunction() {
        return RouterFunctions.route(RequestPredicates.path("/fallback"), hystrixFallbackHandler);
    }

    @Bean
    public RouterFunction swaggerFunction() {
        RouterFunction routes =
                RouterFunctions.route(RequestPredicates.GET("/configuration/ui"), swaggerUiHandler)
                        .andRoute(
                                RequestPredicates.GET("/configuration/security"),
                                swaggerSecurityHandler);

        routes = routes.andRoute(RequestPredicates.GET(""), swaggerResourceHandler);

        return RouterFunctions.nest(RequestPredicates.path("/swagger-resources"), routes);
    }
}
