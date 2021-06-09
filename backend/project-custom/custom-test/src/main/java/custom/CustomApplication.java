package custom;

import common.swagger.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author zack <br>
 * @create 2021-06-01 18:21 <br>
 * @project custom-test <br>
 */
@EnableCaching
@EnableSwagger
@SpringBootApplication
public class CustomApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomApplication.class, args);
    }
}
