package custom.upms.redis;

import common.redis.key.KeyPrefix;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@NoArgsConstructor
@Getter
public enum RedisSignEnum implements KeyPrefix {
    SIGN_KEY("sign"),
    ;

    private String prefix = KeyPrefix.prefix;

    RedisSignEnum(String prefix) {
        this.prefix += prefix;
    }
}
