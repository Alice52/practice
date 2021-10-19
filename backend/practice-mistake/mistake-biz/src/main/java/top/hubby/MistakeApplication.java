package top.hubby;

import common.swagger.annotation.EnableSwagger;
import common.uid.annotation.EnableUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author asd <br>
 * @create 2021-10-19 2:12 PM <br>
 * @project swagger-3 <br>
 */
@EnableUID
@EnableSwagger
@SpringBootApplication
public class MistakeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MistakeApplication.class, args);
    }
}
