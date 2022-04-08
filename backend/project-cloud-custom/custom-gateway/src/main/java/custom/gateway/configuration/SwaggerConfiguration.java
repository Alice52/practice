package custom.gateway.configuration;

import custom.gateway.configuration.swagger.SwaggerProvider;
import custom.gateway.configuration.swagger.SwaggerResourceHandler;
import custom.gateway.configuration.swagger.SwaggerSecurityHandler;
import custom.gateway.configuration.swagger.SwaggerUiHandler;
import custom.gateway.configuration.swagger.WebFluxConfiguration;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zack <br>
 * @create 2021-06-27<br>
 * @project project-cloud-custom <br>
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        return new SwaggerProvider();
    }

    @Bean
    public SwaggerResourceHandler swaggerResourceHandler() {
        return new SwaggerResourceHandler();
    }

    @Bean
    public SwaggerSecurityHandler swaggerSecurityHandler() {
        return new SwaggerSecurityHandler();
    }

    @Bean
    public SwaggerUiHandler swaggerUiHandler() {
        return new SwaggerUiHandler();
    }

    @Bean
    public WebFluxConfiguration webFluxConfiguration() {
        return new WebFluxConfiguration();
    }
}
