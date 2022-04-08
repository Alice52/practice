package common.cloud.component;

import io.undertow.UndertowOptions;
import org.apache.commons.lang.StringUtils;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.embedded.undertow.ConfigurableUndertowWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

/**
 * @author asd <br>
 * @create 2021-06-29 2:27 PM <br>
 * @project custom-upms-grpc <br>
 */
@Component
public class UndertowWebServerAccessLogTimingEnabler
        implements WebServerFactoryCustomizer<ConfigurableUndertowWebServerFactory> {

    private final ServerProperties serverProperties;

    public UndertowWebServerAccessLogTimingEnabler(ServerProperties serverProperties) {
        this.serverProperties = serverProperties;
    }

    @Override
    public void customize(ConfigurableUndertowWebServerFactory factory) {
        String pattern = serverProperties.getUndertow().getAccesslog().getPattern();

        // Record request timing only if the pattern
        if (requestCost(pattern)) {
            factory.addBuilderCustomizers(
                    builder ->
                            builder.setServerOption(
                                    UndertowOptions.RECORD_REQUEST_START_TIME, true));
        }
    }

    private boolean requestCost(String pattern) {
        if (StringUtils.isBlank(pattern)) {
            return false;
        }
        return pattern.contains("%D") || pattern.contains("%T");
    }
}
