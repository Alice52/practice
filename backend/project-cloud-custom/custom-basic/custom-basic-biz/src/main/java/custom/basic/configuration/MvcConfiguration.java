// package custom.basic.configuration;
//
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
// import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
/// **
// * @author zack <br>
// * @create 2021-06-27<br>
// * @project project-cloud-custom <br>
// */
// @Configuration
// public class MvcConfiguration implements WebMvcConfigurer {
//
//    /**
//     * throw-exception-if-no-handler-found: true <br>
//     * will lead to no handler exception for static resources. <br>
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        // release swagger
//        registry.addResourceHandler("/swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        // release relevant js
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addRedirectViewController("/swagger-ui", "/swagger-ui.html");
//        registry.addRedirectViewController("/api", "/swagger-ui.html");
//    }
// }
