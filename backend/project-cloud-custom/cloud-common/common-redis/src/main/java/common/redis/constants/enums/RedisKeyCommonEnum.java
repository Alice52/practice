package common.redis.constants.enums;

import common.redis.key.KeyPrefix;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2021-06-04 17:16 <br>
 * @project custom-test <br>
 */
@NoArgsConstructor
public enum RedisKeyCommonEnum implements KeyPrefix {
    VERIFY_CODE("verify_code"),
    CACHE_LIMIT("request_limit");

    private String prefix = KeyPrefix.prefix;

    RedisKeyCommonEnum(String prefix) {
        this.prefix += prefix;
    }
}
