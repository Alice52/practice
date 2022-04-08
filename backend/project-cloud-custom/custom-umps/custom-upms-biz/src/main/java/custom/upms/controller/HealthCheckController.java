package custom.upms.controller;

import javax.annotation.Resource;

import common.core.util.R;
import common.redis.utils.RedisUtil;
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
@RequestMapping("/upms/health")
public class HealthCheckController {

    @Resource private RedisUtil redisUtil;

    @GetMapping("/status")
    public R<String> up() {
        return R.success("UP");
    }
}
