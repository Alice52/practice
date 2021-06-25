package custom.controler;

import common.annotation.LogAnno;
import common.core.util.R;
import common.redis.constants.enums.RedisKeyCommonEnum;
import common.redis.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * @author zack <br>
 * @create 2021-06-25 09:57 <br>
 * @project swagger-3 <br>
 */
@Api(tags = {"Redis Sample Api"})
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {

    @Resource private RedisUtil redisUtil;

    @PostMapping
    @ApiOperation("Batch Insert Sample")
    public @NotNull R<Boolean> batchInsert(@RequestParam("count") Integer count) {

        return R.<Boolean>builder()
                .data(redisUtil.batchInsert(RedisKeyCommonEnum.BATCH_DELETE, count))
                .build();
    }

    @LogAnno
    @DeleteMapping
    @ApiOperation("Batch Reg Delete Sample")
    public @NotNull R<Boolean> batchDelete(@RequestParam("key") String key) {

        return R.<Boolean>builder()
                .data(redisUtil.batchDeleteKey(RedisKeyCommonEnum.BATCH_DELETE, 1000, key + "*"))
                .build();
    }
}
