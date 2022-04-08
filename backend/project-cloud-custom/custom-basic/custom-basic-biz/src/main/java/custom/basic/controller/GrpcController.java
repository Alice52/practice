package custom.basic.controller;

import javax.annotation.Resource;

import common.core.util.R;
import custom.basic.grpc.service.client.IUpmsClientService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Resource private IUpmsClientService upmsClientService;

    @GetMapping
    public R<String> sayHello(@RequestParam(defaultValue = "zack") final String name) {
        return R.success(upmsClientService.sayHello(name));
    }
}
