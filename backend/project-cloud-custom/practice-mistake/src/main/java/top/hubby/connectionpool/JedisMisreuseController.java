package top.hubby.connectionpool;

import java.util.concurrent.TimeUnit;

import cn.hutool.core.lang.Assert;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asd <br>
 * @create 2021-10-21 1:59 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Api(tags = "Connection-Pool")
@RestController
@RequestMapping("/pool/jedis")
public class JedisMisreuseController {

    private static JedisPool jedisPool =
            new JedisPool(
                    new JedisPoolConfig(),
                    "redisProperties.getHost()",
                    6379,
                    Protocol.DEFAULT_TIMEOUT,
                    "redisProperties.getPassword()",
                    Protocol.DEFAULT_DATABASE,
                    true);

    // @PostConstruct
    public void init() {
        try (Jedis jedis = new Jedis("127.0.0.1", 6379)) {
            Assert.isTrue("OK".equals(jedis.set("a", "1")), "set a = 1 return OK");
            Assert.isTrue("OK".equals(jedis.set("b", "2")), "set b = 2 return OK");
        }
        Runtime.getRuntime().addShutdownHook(new Thread(() -> jedisPool.close()));
    }

    @GetMapping("/wrong")
    public void wrong() throws InterruptedException {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        new Thread(
                        () -> {
                            for (int i = 0; i < 1000; i++) {
                                String result = jedis.get("a");
                                if (!"1".equals(result)) {
                                    log.warn("Expect a to be 1 but found {}", result);
                                    return;
                                }
                            }
                        })
                .start();
        new Thread(
                        () -> {
                            for (int i = 0; i < 1000; i++) {
                                String result = jedis.get("b");
                                if (!"2".equals(result)) {
                                    log.warn("Expect b to be 2 but found {}", result);
                                    return;
                                }
                            }
                        })
                .start();
        TimeUnit.SECONDS.sleep(5);
    }

    @GetMapping("/right")
    public void right() throws InterruptedException {

        new Thread(
                        () -> {
                            try (Jedis jedis = jedisPool.getResource()) {
                                for (int i = 0; i < 1000; i++) {
                                    String result = jedis.get("a");
                                    if (!"1".equals(result)) {
                                        log.warn("Expect a to be 1 but found {}", result);
                                        return;
                                    }
                                }
                            }
                        })
                .start();
        new Thread(
                        () -> {
                            try (Jedis jedis = jedisPool.getResource()) {
                                for (int i = 0; i < 1000; i++) {
                                    String result = jedis.get("b");
                                    if (!"2".equals(result)) {
                                        log.warn("Expect b to be 2 but found {}", result);
                                        return;
                                    }
                                }
                            }
                        })
                .start();
        TimeUnit.SECONDS.sleep(5);
    }

    @GetMapping("/timeout")
    public String timeout(
            @RequestParam("waittimeout") int waittimeout,
            @RequestParam("conntimeout") int conntimeout) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(1);
        config.setMaxWaitMillis(waittimeout);
        try (JedisPool jedisPool = new JedisPool(config, "127.0.0.1", 6379, conntimeout);
                Jedis jedis = jedisPool.getResource()) {
            return jedis.set("test", "test");
        }
    }
}
