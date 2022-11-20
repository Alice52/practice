package top.hubby.spring.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hubby.connectionpool.database.service.UserService;
import top.hubby.model.entity.User;
import top.hubby.spring.annotation.Metrics;

/**
 * @author asd <br>
 * @create 2021-11-15 3:07 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Api(tags = "Spring")
@RestController
@RequestMapping("/spring/scope")
@Metrics(logParameters = true, logReturn = true, ignoreException = true)
public class AopTransactionController {
    @Autowired private UserService userService;

    @GetMapping("/transaction")
    public Long transaction(@RequestParam("name") String name) {
        userService.createUser(new User(name));
        return userService.getUserCount(name);
    }
}
