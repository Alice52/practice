package top.hubby.job.delay;

import common.swagger.annotation.EnableSwagger;
import common.uid.annotation.EnableUID;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 1. https://mp.weixin.qq.com/s/sGgr3hYGPTr8P7hGJtreEg<br>
 * 2. https://mp.weixin.qq.com/s/kfLZh665_et6eCd_D0jzmQ<br>
 *
 * @author asd <br>
 * @create 2021-11-26 1:26 PM <br>
 * @project project-cloud-custom <br>
 */
@EnableScheduling
@EnableUID
@EnableSwagger
@SpringBootApplication
@MapperScan("top.hubby.job.delay.domain.order.repository")
public class DelayTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DelayTaskApplication.class, args);
    }
}
