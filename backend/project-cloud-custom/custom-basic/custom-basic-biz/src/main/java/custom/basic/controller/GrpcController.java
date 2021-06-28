package custom.basic.controller;

import custom.basic.grpc.service.client.UpmsClientService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author asd <br>
 * @create 2021-06-28 5:04 PM <br>
 * @project common-core <br>
 */
@Slf4j
@Api(tags = "Grpc Sample")
@RestController
@RequestMapping("/basic/grpc")
public class GrpcController {

    @Resource private UpmsClientService upmsClientService;

    @RequestMapping
    public String sayHello(@RequestParam(defaultValue = "Zack") final String name) {
        return upmsClientService.sayHello(name);
    }
}
