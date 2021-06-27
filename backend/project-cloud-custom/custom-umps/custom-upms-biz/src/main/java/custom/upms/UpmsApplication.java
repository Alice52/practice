package custom.upms;

import common.cloud.annotation.CustomCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * @author zack <br>
 * @create 2021-06-27<br>
 * @project project-cloud-custom <br>
 */
@CustomCloudApplication
public class UpmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }
}
