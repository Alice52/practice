package top.hubby;

import common.swagger.annotation.EnableSwagger;
import common.uid.annotation.EnableUID;
import org.mybatis.spring.annotation.MapperScan;

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
@MapperScan("top.hubby.connectionpool.database.mapper")
public class MistakeApplication {
    public static void main(String[] args) {
        SpringApplication.run(MistakeApplication.class, args);
    }
}
