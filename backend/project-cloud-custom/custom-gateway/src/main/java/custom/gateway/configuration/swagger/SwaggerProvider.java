package custom.gateway.configuration.swagger;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.support.NameUtils;

/**
 * @author zack <br>
 * @create 2021-06-26<br>
 * @project project-cloud-custom <br>
 */
@Slf4j
public class SwaggerProvider implements SwaggerResourcesProvider {
    public static final String API_URI = "/v2/api-docs";

    @Resource private RouteDefinitionLocator routeDefinitionLocator;

    private static boolean ignorePathCase(PredicateDefinition predicateDefinition) {

        return StrUtil.equalsIgnoreCase("Path", predicateDefinition.getName());
    }

    /**
     * Get api docs location, such as convert /basic/** to /basic/v2/api-docs
     *
     * @param predicateDefinition
     * @return
     */
    private static String getLocation(PredicateDefinition predicateDefinition) {

        return predicateDefinition
                .getArgs()
                .get(NameUtils.GENERATED_NAME_PREFIX + "0")
                .replace("/**", API_URI);
    }

    private static SwaggerResource newSwaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<RouteDefinition> routes =
                CompletableFuture.supplyAsync(
                                // subscribe is async
                                () ->
                                        routeDefinitionLocator
                                                .getRouteDefinitions()
                                                .collectList()
                                                .block())
                        .join();

        routes.forEach(
                routeDefinition ->
                        routeDefinition.getPredicates().stream()
                                .filter(SwaggerProvider::ignorePathCase)
                                .forEach(
                                        y ->
                                                resources.add(
                                                        newSwaggerResource(
                                                                routeDefinition.getId(),
                                                                getLocation(y)))));

        return resources.stream()
                .sorted(Comparator.comparing(SwaggerResource::getName))
                .collect(Collectors.toList());
    }
}
