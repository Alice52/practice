package top.hubby.custom.test.config;

import common.redis.aspect.IdempotentRequestAspect;
import common.redis.aspect.LimitRequestAspect;
import common.redis.aspect.RedisLockAspect;
import common.redis.configuration.RedisConfiguration;
import common.redis.utils.RedisUtil;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author zack <br>
 * @create 2022-04-08<br>
 * @project project-cloud-custom <br>
 */
@Configuration
@EnableAutoConfiguration(
        exclude = {
            RedisAutoConfiguration.class,
            RedisRepositoriesAutoConfiguration.class,
            RedissonAutoConfiguration.class,
            RedisConfiguration.class,
            IdempotentRequestAspect.class,
            LimitRequestAspect.class,
            RedisLockAspect.class,
            RedisUtil.class
        })
public class ExcludeRedisConfig {}
