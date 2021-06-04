package common.redis.utils;

import cn.hutool.core.util.ObjectUtil;
import common.redis.key.KeyPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
    private static String module;
    @Resource private RedisTemplate<String, Object> redisTemplate;

    @Value("${common.core.redis.expire-time:0}")
    private long defaultExpireTime;

    /**
     * build key by prefix and addition info with modules.
     *
     * @param prefix
     * @param keys
     * @return
     */
    public static String buildKey(KeyPrefix prefix, String... keys) {
        return RedisKeyUtil.buildKey(module, prefix, keys);
    }

    /**
     * build remove key by prefix and addition info with modules.
     *
     * @param prefix
     * @param keys
     * @return
     */
    private static String buildDeleteKey(KeyPrefix prefix, String... keys) {
        return RedisKeyUtil.buildDeleteKey(module, prefix, keys);
    }

    @Value("${common.core.redis.module:common}")
    public void setModule(String module) {
        RedisUtil.module = module;
    }

    @Deprecated
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Boolean hasKey(KeyPrefix prefix, String... key) {

        return redisTemplate.hasKey(buildKey(prefix, key));
    }

    @Deprecated
    public Long increment(String key, long delta) {

        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * atomic update value.
     *
     * @param prefix
     * @param delta
     * @param key
     * @return
     */
    public Long increment(KeyPrefix prefix, long delta, String... key) {

        return redisTemplate.opsForValue().increment(buildKey(prefix, key), delta);
    }

    /**
     * atomic update value and expire info.
     *
     * @param prefix
     * @param delta
     * @param seconds
     * @param unit
     * @param keys
     * @return
     */
    public long increment(
            KeyPrefix prefix, long delta, final long seconds, final TimeUnit unit, String... keys) {

        Long increment = increment(prefix, delta, keys);
        boolean expire = expire(buildKey(prefix, keys), seconds, unit);

        if (expire) {
            return increment;
        }

        return 0;
    }

    /**
     * set value and default expire in config time.<br>
     * unit is {@link TimeUnit#HOURS}
     *
     * @param prefix
     * @param e
     * @param keys
     * @param <E>
     */
    @Deprecated
    public <E> void set(KeyPrefix prefix, E e, String... keys) {

        set(e, buildKey(prefix, keys), defaultExpireTime, TimeUnit.HOURS);
    }

    /**
     * set k-v with expire info.
     *
     * @param prefix
     * @param e
     * @param seconds
     * @param unit
     * @param keys
     * @param <E>
     */
    public <E> void set(
            KeyPrefix prefix, E e, final long seconds, final TimeUnit unit, String... keys) {

        set(e, buildKey(prefix, keys), seconds, unit);
    }

    /**
     * expire specified key.
     *
     * @param prefix
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public Boolean expire(KeyPrefix prefix, String key, final long timeout, final TimeUnit unit) {

        return redisTemplate.expire(buildKey(prefix, key), timeout, unit);
    }

    /**
     * get specified key.
     *
     * @param prefix
     * @param clazz
     * @param key
     * @param <E>
     * @return
     */
    public <E> E get(KeyPrefix prefix, Class<E> clazz, String... key) {

        return get(buildKey(prefix, key), clazz);
    }

    /**
     * remove keys.
     *
     * @param prefix
     */
    public void removeAll(KeyPrefix prefix) {
        Set<String> keys = redisTemplate.keys(buildDeleteKey(prefix, "*"));
        Optional.ofNullable(keys).ifPresent(x -> redisTemplate.delete(keys));
    }

    /**
     * Remove all cache keys found by makers.
     *
     * @param prefix
     * @param keys
     */
    public void remove(KeyPrefix prefix, String... keys) {
        Arrays.stream(keys).forEach(x -> remove(prefix, x));
    }

    /**
     * Set specified key expiration.
     *
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    private Boolean expire(String key, final long timeout, final TimeUnit unit) {

        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * real set key with expire.
     *
     * @param obj
     * @param realKey
     * @param timeout
     * @param unit
     */
    private void set(Object obj, String realKey, final long timeout, final TimeUnit unit) {
        Optional.ofNullable(obj)
                .ifPresent(
                        x -> {
                            if (timeout == 0) {
                                redisTemplate.opsForValue().set(realKey, obj);
                            } else {
                                redisTemplate.opsForValue().set(realKey, obj, timeout, unit);
                            }
                        });
    }

    /**
     * get specified k's value.
     *
     * @param realKey
     * @param <E>
     * @return
     */
    private <E> E get(String realKey, Class<E> clazz) {

        Object object = redisTemplate.opsForValue().get(realKey);
        if (ObjectUtil.isNotNull(object)) {
            E e = null;
            try {
                e = clazz.cast(object);
            } catch (ClassCastException ex) {
                log.warn("cast object: {} to type: {} error: {}", object, clazz, ex);
            }

            return e;
        }

        return null;
    }

    /**
     * batch remove keys.
     *
     * @param prefix
     * @param x
     */
    private void remove(KeyPrefix prefix, String x) {
        Set<String> keySet = redisTemplate.keys(buildDeleteKey(prefix, x));
        Optional.ofNullable(keySet).ifPresent(y -> redisTemplate.delete(keySet));
    }
}