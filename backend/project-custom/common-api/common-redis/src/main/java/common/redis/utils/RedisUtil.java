package common.redis.utils;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import common.redis.key.KeyPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * // TODO:
 *
 * <pre>
 *     1. need add exception catch and handle
 *     2. 30 * 6 need configured
 *     3. remove expire from KeyPrefix
 * </pre>
 *
 * @author zack <br>
 * @create 2021-06-03 14:26 <br>
 * @project custom-test <br>
 */
@Component
@Slf4j
public class RedisUtil {
    @Resource private RedisTemplate<String, Object> redisTemplate;

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public boolean hasKey(KeyPrefix prefix, String... key) {

        return redisTemplate.hasKey(RedisKeyUtil.buildKey(prefix, key));
    }

    @Deprecated
    public long increment(String key, long delta) {

        return redisTemplate.opsForValue().increment(key, delta);
    }

    public long increment(KeyPrefix prefix, long delta, String... key) {

        return redisTemplate.opsForValue().increment(RedisKeyUtil.buildKey(prefix, key), delta);
    }

    public long increment(
            KeyPrefix prefix, long delta, final long seconds, final TimeUnit unit, String... keys) {

        String key = RedisKeyUtil.buildKey(prefix, keys);
        Long increment = redisTemplate.opsForValue().increment(key, delta);
        boolean expire = expire(key, seconds, unit);

        if (expire) {
            return increment;
        }

        return 0;
    }

    public <E> void set(KeyPrefix prefix, E e, String... key) {
        String realKey = RedisKeyUtil.buildKey(prefix, key);
        set(e, realKey, 30 * 6, TimeUnit.DAYS);
    }

    public <E> void set(
            E e, final long seconds, final TimeUnit unit, KeyPrefix prefix, String... key) {

        String realKey = RedisKeyUtil.buildKey(prefix, key);
        set(e, realKey, seconds, unit);
    }

    private void set(Object obj, String realKey, final long timeout, final TimeUnit unit) {
        Optional.ofNullable(obj)
                .ifPresent(x -> redisTemplate.opsForValue().set(realKey, obj, timeout, unit));
    }

    public boolean expire(KeyPrefix prefix, String key, final long timeout, final TimeUnit unit) {

        return redisTemplate.expire(RedisKeyUtil.buildKey(prefix, key), timeout, unit);
    }

    public <E> E get(KeyPrefix prefix, String... key) {

        return get(RedisKeyUtil.buildKey(prefix, key));
    }

    public <E> E get(KeyPrefix prefix, String key) {

        String realKey = RedisKeyUtil.buildKey(prefix, key);
        return get(realKey);
    }

    private <E> E get(String realKey) {

        Object object = redisTemplate.opsForValue().get(realKey);
        if (ObjectUtil.isNotNull(object)) {
            E e = null;
            try {
                e = (E) object;
            } catch (Exception ex) {
                log.warn("cast object: {} to type: {} error: {}", object, e.getClass(), ex);
            }

            return e;
        }

        return null;
    }

    public void removeAll(KeyPrefix prefix) {
        Set<String> keys = redisTemplate.keys(RedisKeyUtil.buildDeleteKey(prefix, "*"));
        Optional.ofNullable(keys).ifPresent(x -> redisTemplate.delete(keys));
    }

    /**
     * Remove all cache keys found by makers.
     *
     * @param prefix
     * @param key
     * @param keys
     */
    public void remove(KeyPrefix prefix, String key, String... keys) {
        Arrays.stream(ArrayUtil.append(keys, key)).forEach(x -> remove(prefix, x));
    }

    private void remove(KeyPrefix prefix, String x) {
        Set<String> keySet = redisTemplate.keys(RedisKeyUtil.buildDeleteKey(prefix, x));
        Optional.ofNullable(keySet).ifPresent(y -> redisTemplate.delete(keySet));
    }

    /**
     * Set specified key expiration.
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    private boolean expire(String key, final long timeout, final TimeUnit unit) {

        return redisTemplate.expire(key, timeout, unit);
    }
}
