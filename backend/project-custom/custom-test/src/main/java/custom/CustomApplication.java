package custom;

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
@EnableUID
@EnableSwagger
@SpringBootApplication
@MapperScan("custom.mapper")
public class CustomApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomApplication.class, args);
    }
}
