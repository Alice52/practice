package practice.job.dynamic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author asd <br>
 * @create 2021-12-23 11:01 AM <br>
 * @project project-cloud-custom <br>
 */
@MapperScan("practice.job.dynamic.mapper")
@EnableScheduling
@SpringBootApplication
public class DynamicJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicJobApplication.class, args);
    }
}
