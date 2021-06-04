package common.redis.utils;

import cn.hutool.core.util.StrUtil;
import common.redis.key.KeyPrefix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zack <br>
 * @create 2021-06-03 14:32 <br>
 * @project custom-test <br>
 */
@Component
public final class RedisKeyUtil {

    private static String module;

    @Value("${common.core.redis.module:common}")
    public void setRequestIdKey(String module) {
        RedisKeyUtil.module = module;
    }

    public static String buildDeleteKey(KeyPrefix prefix, String... params) {

        return buildKey(module, prefix, params);
    }

    public static String buildKey(KeyPrefix prefix, String... params) {

        return buildKey(module, prefix, params);
    }

    protected static String buildKey(String module, KeyPrefix prefix, String... params) {

        StringBuilder realKey =
                new StringBuilder(
                        StrUtil.removeSuffix(
                                StrUtil.format(prefix.getPrefix(), module), StrUtil.COLON));
        for (String param : params) {
            realKey.append(StrUtil.COLON).append(param);
        }

        return realKey.toString();
    }
}
