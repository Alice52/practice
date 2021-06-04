package custom.controler;

import common.annotation.LogAnno;
import common.core.annotation.LocalIdempotentRequest;
import common.core.annotation.LocalLimitRequest;
import common.core.exception.assertion.IBaseAssert;
import common.core.util.R;
import common.redis.annotation.RedisIdempotentRequest;
import common.redis.annotation.RedisLimitRequest;
import common.redis.annotation.RedisLock;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.concurrent.TimeUnit;

/**
 * @author zack <br>
 * @create 2021-06-03 16:38 <br>
 * @project custom-test <br>
 */
@Api(tags = {"Sample Usage Api"})
@RestController
@RequestMapping("/sample")
@Slf4j
public class SampleController {
    @LogAnno
    @GetMapping("/exception")
    @ApiOperation("@LogAnno & Exception Strategy Sample")
    public R<Void> exception() {

        IBaseAssert.assertFail2Response();
        // CommonResponseEnum.BEAN_VALIDATION.assertFail();

        return R.success();
    }

    @SneakyThrows
    @RedisLock(timeOut = 500)
    @GetMapping("/lock")
    @ApiOperation("@RedisLock Sample")
    public @NotNull R<Void> lock() {

        TimeUnit.MINUTES.sleep(1);

        return R.success();
    }

    @LocalLimitRequest(count = 10, time = 1, timeUnit = TimeUnit.MINUTES)
    @GetMapping("/local-limit")
    @ApiOperation("@LocalLimitRequest Sample")
    public @NotNull R<Void> localLimitRequest() {

        return R.success();
    }

    @RedisLimitRequest(count = 10, time = 1, timeUnit = TimeUnit.MINUTES)
    @GetMapping("/redis-limit")
    @ApiOperation("@RedisLimitRequest Sample")
    public @NotNull R<Void> redisLimitRequest() {

        return R.success();
    }

    @LocalIdempotentRequest(time = 1, timeUnit = TimeUnit.MINUTES)
    @GetMapping("/local-idempotent")
    @ApiOperation("@LocalIdempotentRequest Sample")
    public @NotNull R<Void> localIdempotentRequest() {

        return R.success();
    }

    @RedisIdempotentRequest(time = 1, timeUnit = TimeUnit.MINUTES)
    @GetMapping("/redis-idempotent")
    @ApiOperation("@RedisIdempotentRequest Sample")
    public @NotNull R<Void> redisIdempotentRequest() {

        return R.success();
    }
}