package common.redis.key;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2021-06-03 14:28 <br>
 * @project custom-test <br>
 */
@Deprecated
@AllArgsConstructor
@NoArgsConstructor
public abstract class BasePrefix implements KeyPrefix {

    private String prefix;

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
