package custom.upms;

import common.cloud.annotation.CustomCloudApplication;
import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;

/**
 * @author zack <br>
 * @create 2021-06-27<br>
 * @project project-cloud-custom <br>
 */
@CustomCloudApplication
@MapperScan("custom.upms.mapper")
public class UpmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UpmsApplication.class, args);
    }
}
