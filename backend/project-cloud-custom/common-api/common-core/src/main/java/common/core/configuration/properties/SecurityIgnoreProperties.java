package common.core.configuration.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zack <br/>
 * @create 2021-06-26<br/>
 * @project project-cloud-custom <br/>
 */
@Data
@Configuration
// TODO: @RefreshScope
@ConditionalOnExpression("!'${common.core.ignore}'.isEmpty()")
@ConfigurationProperties(prefix = "common.core.ignore")
public class SecurityIgnoreProperties {
    /**
     * 放行终端配置，网关不校验此处的终端
     */
    private List<String> clients = new ArrayList<>();
    /**
     * 放行url,放行的url不再被安全框架拦截
     *
     * @Deprecated 使用ignoreMatchers替代
     */
    @Deprecated
    private List<String> urls = new ArrayList<>();
    /**
     * 不聚合swagger
     */
    private List<String> swaggerProviders = new ArrayList<>();

    /**
     * 放行url列表
     */
    private List<IgnoreMatcher> ignoreMatchers = new ArrayList<>();
}

