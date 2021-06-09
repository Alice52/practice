package custom;

import common.swagger.annotation.EnableSwagger;
import io.micrometer.core.instrument.util.StringUtils;
import io.undertow.UndertowOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author zack <br>
 * @create 2021-06-01 18:21 <br>
 * @project custom-test <br>
 */
@EnableSwagger
@SpringBootApplication
public class CustomApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomApplication.class, args);
    }

    @Component
    class UndertowWebServerAccessLogTimingEnabler
            implements WebServerFactoryCustomizer<ConfigurableUndertowWebServerFactory> {

        private final ServerProperties serverProperties;

        public UndertowWebServerAccessLogTimingEnabler(ServerProperties serverProperties) {
            this.serverProperties = serverProperties;
        }

        @Override
        public void customize(ConfigurableUndertowWebServerFactory factory) {
            String pattern = serverProperties.getUndertow().getAccesslog().getPattern();

            // Record request timing only if the pattern
            if (logRequestProcessingTiming(pattern)) {
                factory.addBuilderCustomizers(
                        builder ->
                                builder.setServerOption(
                                        UndertowOptions.RECORD_REQUEST_START_TIME, true));
            }

            // Custom access log receiver.
            //            factory.addDeploymentInfoCustomizers(
            //                    deploymentInfo -> {
            //                        deploymentInfo.addInitialHandlerChainWrapper(
            //                                handler -> {
            //                                    Slf4jAccessLogReceiver accessLogReceiver =
            //                                            new Slf4jAccessLogReceiver();
            //                                    return new AccessLogHandler(
            //                                            handler,
            //                                            accessLogReceiver,
            //                                            pattern,
            //                                            Undertow.class.getClassLoader());
            //                                });
            //                    });
        }

        private boolean logRequestProcessingTiming(String pattern) {
            if (StringUtils.isBlank(pattern)) {
                return false;
            }
            return pattern.contains("%D") || pattern.contains("%T");
        }
    }
}
