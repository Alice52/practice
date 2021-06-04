package common.redis.key;

import cn.hutool.core.util.StrUtil;

/**
 * @author zack <br>
 * @create 2021-06-03 14:28 <br>
 * @project custom-test <br>
 */
public interface KeyPrefix {

    String prefix = "project:{}:";

    /**
     * set key prefix.
     *
     * @return String
     */
    default String getPrefix() {
        String className = getClass().getSimpleName();
        return className + StrUtil.COLON + prefix;
    }
}
