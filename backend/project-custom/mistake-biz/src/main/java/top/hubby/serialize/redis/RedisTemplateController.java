package top.hubby.serialize.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author asd <br>
 * @create 2021-10-29 1:39 PM <br>
 * @project swagger-3 <br>
 */
@Api(tags = "Serialize")
@Slf4j
@RestController
@RequestMapping("/serialize/redis")
public class RedisTemplateController {

    @Autowired private RedisTemplate redisTemplate;
    @Autowired private StringRedisTemplate stringRedisTemplate;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private RedisTemplate<String, User> userRedisTemplate;
    @Autowired private RedisTemplate<String, Long> countRedisTemplate;

    @SneakyThrows
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "1-value");
        map.put(2, "2-value");

        ObjectMapper mapper = new ObjectMapper();
        byte[] bytes = mapper.writeValueAsBytes(map);
        Object o = mapper.readValue(bytes, HashMap.class);

        map = (HashMap<Integer, String>) o;
        log.info("map: {}", map);

        byte[] asBytes = mapper.writeValueAsBytes(new Integer(10));
        Object obj = mapper.readValue(asBytes, String.class);
        log.info("Integer: {}", obj);
    }

    @PostConstruct
    public void init() throws IOException {
        redisTemplate.opsForValue().set("redisTemplate", new User("zhuye", 36));
        stringRedisTemplate
                .opsForValue()
                .set("stringRedisTemplate", objectMapper.writeValueAsString(new User("zhuye", 36)));
        MapKeyIsIntIssue();
    }

    // 不雅使用 int 作为 key 要是有 string
    private void MapKeyIsIntIssue() {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "1-value");
        map.put(2, "2-value");

        redisTemplate.opsForValue().set("map", map);
        // 此时的 map key 会变成 string
        map = (HashMap<Integer, String>) redisTemplate.opsForValue().get("map");
        log.info("map: {}", map);
    }

    @GetMapping("/wrong")
    public void wrong() {
        log.info("redisTemplate get {}", redisTemplate.opsForValue().get("stringRedisTemplate"));
        log.info(
                "stringRedisTemplate get {}",
                stringRedisTemplate.opsForValue().get("redisTemplate"));
    }

    @GetMapping("/right")
    public void right() throws IOException {
        User userFromRedisTemplate = (User) redisTemplate.opsForValue().get("redisTemplate");
        log.info("redisTemplate get {}", userFromRedisTemplate);
        User userFromStringRedisTemplate =
                objectMapper.readValue(
                        stringRedisTemplate.opsForValue().get("stringRedisTemplate"), User.class);
        log.info("stringRedisTemplate get {}", userFromStringRedisTemplate);
    }

    @GetMapping("/right2")
    public void right2() {
        User user = new User("zhuye", 36);
        userRedisTemplate.opsForValue().set(user.getName(), user);
        User userFromRedis = userRedisTemplate.opsForValue().get(user.getName());
        log.info("userRedisTemplate get {} {}", userFromRedis, userFromRedis.getClass());
        log.info(
                "stringRedisTemplate get {}",
                stringRedisTemplate.opsForValue().get(user.getName()));
    }

    @GetMapping("/wrong2")
    public void wrong2() {
        String key = "testCounter";
        countRedisTemplate.opsForValue().set(key, 1L);
        log.info(
                "{} {}",
                countRedisTemplate.opsForValue().get(key),
                countRedisTemplate.opsForValue().get(key) instanceof Long);
        Long l1 = getLongFromRedis(key);
        countRedisTemplate.opsForValue().set(key, Integer.MAX_VALUE + 1L);
        log.info(
                "{} {}",
                countRedisTemplate.opsForValue().get(key),
                countRedisTemplate.opsForValue().get(key) instanceof Long);
        Long l2 = getLongFromRedis(key);
        log.info("{} {}", l1, l2);
    }

    private Long getLongFromRedis(String key) {
        Object o = countRedisTemplate.opsForValue().get(key);
        if (o instanceof Integer) {
            return ((Integer) o).longValue();
        }
        if (o instanceof Long) {
            return (Long) o;
        }
        return null;
    }
}
