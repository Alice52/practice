package dtx.lcn;

import common.swagger.annotation.EnableSwagger;
import common.uid.annotation.EnableUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zack <br>
 * @create 2022-04-29 12:19 <br>
 * @project project-cloud-custom <br>
 */
@EnableUID
@EnableSwagger
@SpringBootApplication
public class PayApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayApplication.class, args);
    }
}
