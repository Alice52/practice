package common.redis.utils;

import cn.hutool.core.util.StrUtil;
import common.redis.key.KeyPrefix;

/**
 * @author zack <br>
 * @create 2021-06-03 14:32 <br>
 * @project custom-test <br>
 */
public final class RedisKeyUtil {
    public static String buildDeleteKey(KeyPrefix prefix, String id, String... params) {

        return buildKey(prefix, id, params);
    }

    public static String buildKey(KeyPrefix prefix, String id, String... params) {

        StringBuilder realKey =
                new StringBuilder(
                        StrUtil.removeSuffix(
                                StrUtil.format(prefix.getPrefix(), id), StrUtil.COLON));
        for (String param : params) {
            realKey.append(StrUtil.COLON).append(param);
        }

        return realKey.toString();
    }
}
