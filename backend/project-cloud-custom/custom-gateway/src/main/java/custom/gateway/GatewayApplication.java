package custom.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author zack <br>
 * @create 2021-06-26<br>
 * @project project-cloud-custom <br>
 */
@RefreshScope
@SpringCloudApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
