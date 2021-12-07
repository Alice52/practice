package custom;

import common.http.annotation.EnableHttpClient;
import common.swagger.annotation.EnableSwagger;
import common.uid.annotation.EnableUID;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zack <br>
 * @create 2021-06-01 18:21 <br>
 * @project custom-test <br>
 */
@EnableHttpClient
@EnableUID
@EnableSwagger
@SpringBootApplication
@MapperScan("custom.mapper")
// @ComponentScan("custom")
public class CustomApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomApplication.class, args);
    }
}
