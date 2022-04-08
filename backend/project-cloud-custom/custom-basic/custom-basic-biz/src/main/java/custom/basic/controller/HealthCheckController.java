package custom.basic.controller;

import common.core.util.R;
import io.swagger.annotations.Api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zack <br>
 * @create 2021-06-27<br>
 * @project project-cloud-custom <br>
 */
@Api(tags = "Health Check")
@RestController
@RequestMapping("/basic/health")
public class HealthCheckController {

    @GetMapping("/status")
    public R<String> up() {
        return R.success("UP");
    }
}
