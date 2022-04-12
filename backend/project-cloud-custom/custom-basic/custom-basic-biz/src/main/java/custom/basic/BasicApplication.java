package custom.basic;

import common.cloud.annotation.CustomCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * @author zack <br>
 * @create 2021-06-26<br>
 * @project project-cloud-custom <br>
 */
@CustomCloudApplication
public class BasicApplication {
    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }
}
