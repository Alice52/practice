package cn.edu.ntu.common.api.autoconfiguration;

import cn.edu.ntu.common.api.properties.SwaggerProperties;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zack <br>
 * @create 2020-05-17 16:36 <br>
 * @project springboot <br>
 */
@EnableOpenApi
@Configuration
@ConditionalOnProperty(
    prefix = "swagger3",
    value = {"enable"},
    havingValue = "true")
@EnableConfigurationProperties(SwaggerProperties.class)
@ComponentScan(basePackages = {"cn.edu.ntu.common.api"})
public class SwaggerAutoConfiguration implements WebMvcConfigurer {

  @Resource SwaggerProperties swaggerProperties;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // release swagger
    registry
        .addResourceHandler("/swagger**/**")
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/v3/**").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/doc.html").addResourceLocations("classpath:/META-INF/resources/");
    // release relevant js
    registry
        .addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.OAS_30)
        .pathMapping("/")
        .groupName(swaggerProperties.getGroupName())
        .enable(swaggerProperties.getEnable())
        .apiInfo(apiInfo())
        .host(swaggerProperties.getTryHost())
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
        // .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
        .protocols(newHashSet("https", "http"))
        .securitySchemes(securitySchemes())
        .securityContexts(securityContexts());
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(swaggerProperties.getApplicationName() + " Api Doc")
        .description(swaggerProperties.getApplicationDescription())
        .contact(new Contact(swaggerProperties.getAuthor(), null, swaggerProperties.getEmail()))
        .version(
            "Application Version: "
                + swaggerProperties.getVersion()
                + ", Spring Boot Version: "
                + SpringBootVersion.getVersion())
        .build();
  }

  private List<SecurityScheme> securitySchemes() {
    ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
    return Collections.singletonList(apiKey);
  }

  private List<SecurityContext> securityContexts() {
    return Collections.singletonList(
        SecurityContext.builder()
            .securityReferences(
                Collections.singletonList(
                    new SecurityReference(
                        "BASE_TOKEN",
                        new AuthorizationScope[] {new AuthorizationScope("global", "")})))
            .build());
  }

  @SafeVarargs
  private final <T> Set<T> newHashSet(T... ts) {
    if (ts.length > 0) {
      return new LinkedHashSet<>(Arrays.asList(ts));
    }
    return null;
  }
}
