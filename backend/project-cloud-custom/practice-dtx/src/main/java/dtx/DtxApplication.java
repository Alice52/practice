package dtx;

import common.swagger.annotation.EnableSwagger;
import common.uid.annotation.EnableUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zack <br>
 * @create 2022-04-09 10:30 <br>
 * @project project-cloud-custom <br>
 */
@EnableUID
@EnableSwagger
@SpringBootApplication
public class DtxApplication {
    public static void main(String[] args) {
        SpringApplication.run(DtxApplication.class, args);
    }
}
