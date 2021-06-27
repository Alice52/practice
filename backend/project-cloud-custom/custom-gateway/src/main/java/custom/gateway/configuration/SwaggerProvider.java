package custom.gateway.configuration;

import common.core.configuration.properties.SecurityIgnoreProperties;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zack <br/>
 * @create 2021-06-26<br/>
 * @project project-cloud-custom <br/>
 */
@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {
    public static final String API_URI = "/v2/api-docs";

    @Resource
    private RouteDefinitionLocator routeDefinitionLocator;

    @Resource
    private SecurityIgnoreProperties ignorePropertiesConfig;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<RouteDefinition> routes = new ArrayList<>();
        routeDefinitionLocator.getRouteDefinitions().subscribe(routes::add);
        routes.forEach(routeDefinition ->
                routeDefinition.getPredicates().stream()
                        .filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
                        .filter(predicateDefinition -> !ignorePropertiesConfig.getSwaggerProviders().contains(routeDefinition.getId()))
                        .forEach(predicateDefinition -> resources.add(newSwaggerResource(routeDefinition.getId(),
                                predicateDefinition.getArgs()
                                        .get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                        .replace("/**", API_URI))))
        );

        return resources.stream().sorted(Comparator.comparing(SwaggerResource::getName))
                .collect(Collectors.toList());
    }

    private SwaggerResource newSwaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
