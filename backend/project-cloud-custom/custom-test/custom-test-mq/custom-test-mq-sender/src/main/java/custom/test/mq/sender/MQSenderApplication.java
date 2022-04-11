package custom.test.mq.sender;

import common.swagger.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import top.hubby.custom.test.config.ExcludeRedisConfig;
import top.hubby.mq.sender.annotation.EnableMQSender;

/**
 * @author zack <br>
 * @create 2022-04-11 20:45 <br>
 * @project project-cloud-custom <br>
 */
@EnableMQSender
@EnableSwagger
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Import(ExcludeRedisConfig.class)
public class MQSenderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MQSenderApplication.class, args);
    }
}
