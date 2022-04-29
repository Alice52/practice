package dtx.lcn;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import common.swagger.annotation.EnableSwagger;
import common.uid.annotation.EnableUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zack <br>
 * @create 2022-04-29 12:18 <br>
 * @project project-cloud-custom <br>
 */
@EnableUID
@EnableSwagger
@SpringBootApplication
@EnableDistributedTransaction
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
