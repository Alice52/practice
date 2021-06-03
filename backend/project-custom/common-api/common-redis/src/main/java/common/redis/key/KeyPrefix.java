package common.redis.key;

/**
 * @author zack <br>
 * @create 2021-06-03 14:28 <br>
 * @project custom-test <br>
 */
public interface KeyPrefix {

    String prefix = "project:{}:";

    /**
     * set expire time.
     *
     * @return int
     */
    @Deprecated
    default int expireSeconds() {
        return 0;
    }

    /**
     * set key prefix.
     *
     * @return String
     */
    String getPrefix();
}
