package dtx.lcm;

import com.codingapi.txlcn.tm.config.EnableTransactionManagerServer;
import common.swagger.annotation.EnableSwagger;
import common.uid.annotation.EnableUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zack <br>
 * @create 2022-04-29 12:41 <br>
 * @project project-cloud-custom <br>
 */
@EnableUID
@EnableSwagger
@SpringBootApplication
@EnableTransactionManagerServer
public class TxManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TxManagerApplication.class, args);
    }
}
