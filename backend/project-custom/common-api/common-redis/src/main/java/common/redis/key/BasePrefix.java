package common.redis.key;

/**
 * @author zack <br>
 * @create 2021-06-03 14:28 <br>
 * @project custom-test <br>
 */
public abstract class BasePrefix implements KeyPrefix {

    @Deprecated private int expireSeconds;

    private String prefix;

    /**
     * 0 is represent never expire.
     *
     * @param prefix
     */
    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    public BasePrefix(int expireSeconds, String prefix) {
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }

    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
