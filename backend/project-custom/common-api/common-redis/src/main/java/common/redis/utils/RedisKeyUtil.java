package common.redis.utils;

import cn.hutool.core.util.StrUtil;
import common.redis.key.KeyPrefix;

/**
 * @author zack <br>
 * @create 2021-06-03 14:32 <br>
 * @project custom-test <br>
 */
public final class RedisKeyUtil {

    public static String buildDeleteKey(String module, KeyPrefix prefix, String... params) {

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
