package top.hubby.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zack <br>
 * @create 2021-09-14<br>
 * @project pattern <br>
 */
@Slf4j
public enum EnumSingleton {
    INSTANCE;

    private static final AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }

    public void hash() {
        log.info("hashcode: {}", this.hashCode());
    }
}
