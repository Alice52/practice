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

    public boolean hasKey(KeyPrefix prefix, String maker, String... key) {

        return redisTemplate.hasKey(RedisKeyUtil.buildKey(prefix, maker, key));
    }

    public long increment(long delta, KeyPrefix prefix, String maker, String... key) {

        return redisTemplate
                .opsForValue()
                .increment(RedisKeyUtil.buildKey(prefix, maker, key), delta);
    }

    public <E> void set(E e, KeyPrefix prefix, String maker, String... key) {
        String realKey = RedisKeyUtil.buildKey(prefix, maker, key);
        set(e, realKey, 30 * 6, TimeUnit.DAYS);
    }

    public <E> void set(
            E e,
            final long seconds,
            final TimeUnit unit,
            KeyPrefix prefix,
            String maker,
            String... key) {
        String realKey = RedisKeyUtil.buildKey(prefix, maker, key);
        set(e, realKey, seconds, unit);
    }

    private void set(Object obj, String realKey, final long timeout, final TimeUnit unit) {
        Optional.ofNullable(obj)
                .ifPresent(x -> redisTemplate.opsForValue().set(realKey, obj, timeout, unit));
    }

    public boolean expire(KeyPrefix prefix, String key, final long timeout, final TimeUnit unit) {

        return redisTemplate.expire(RedisKeyUtil.buildKey(prefix, key), timeout, unit);
    }

    public <E> E get(Class<E> clazz, KeyPrefix prefix, String maker, String... key) {

        return get(RedisKeyUtil.buildKey(prefix, maker, key));
    }

    public <E> E get(Class<E> clazz, KeyPrefix prefix, String key) {

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

    public void removeAll(KeyPrefix prefix, String maker) {
        Set<String> keys = redisTemplate.keys(RedisKeyUtil.buildDeleteKey(prefix, maker, "*"));
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
}
