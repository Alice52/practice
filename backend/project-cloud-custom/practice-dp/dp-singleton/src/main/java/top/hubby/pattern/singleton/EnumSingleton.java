package top.hubby.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zack <br>
 * @create 2021-09-14<br>
 * @project pattern <br>
 */
@Slf4j
public enum EnumSingleton {
    INSTANCE;

    public void hash() {
        log.info("hashcode: {}", this.hashCode());
    }
}
