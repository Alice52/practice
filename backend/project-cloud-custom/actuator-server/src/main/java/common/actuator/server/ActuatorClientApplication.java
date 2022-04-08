package common.actuator.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zack <br>
 * @create 2021-06-08<br>
 * @project project-custom <br>
 */
@EnableAdminServer
@SpringBootApplication
public class ActuatorClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorClientApplication.class, args);
    }
}
