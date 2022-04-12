package top.hubby.test.custom.http;

import common.http.annotation.EnableHttpClient;
import common.swagger.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zack <br>
 * @create 2022-04-08 20:04 <br>
 * @project project-cloud-custom <br>
 */
@EnableSwagger
@EnableHttpClient
@SpringBootApplication
public class HttpApplication {
    public static void main(String[] args) {
        SpringApplication.run(HttpApplication.class, args);
    }
}
