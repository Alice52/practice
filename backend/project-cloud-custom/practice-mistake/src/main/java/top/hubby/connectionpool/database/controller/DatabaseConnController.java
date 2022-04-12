package top.hubby.connectionpool.database.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hubby.connectionpool.database.service.UserService;
import top.hubby.model.entity.User;

/**
 * @author asd <br>
 * @create 2021-10-22 10:20 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Api(tags = "Connection-Pool")
@RestController
@RequestMapping("/pool/database")
public class DatabaseConnController {
    @Autowired private UserService userService;

    @GetMapping("/test")
    public User test() {
        User register = userService.register();
        return register;
    }
}
