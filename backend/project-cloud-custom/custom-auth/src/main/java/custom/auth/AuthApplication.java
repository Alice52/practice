package custom.auth;

import common.cloud.annotation.CustomCloudApplication;
import org.springframework.boot.SpringApplication;

/**
 * @author asd <br>
 * @create 2021-06-29 2:24 PM <br>
 * @project custom-auth <br>
 */
@CustomCloudApplication
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
