package top.hubby.pattern.singleton;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
@Slf4j
public class HungrySingletonTests {
    static final int CONCURRENT_COUNT = 10;

    @Test
    public void testLazyConcurrentSafe() {

        for (int i = 0; i < CONCURRENT_COUNT; i++) {
            new Thread(
                            () -> {
                                HungrySingleton instance = HungrySingleton.getInstance();
                                log.info("instance: {}", instance);
                            })
                    .start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
    }

    @Test
    public void testHungry() {
        HungrySingleton instance1 = HungrySingleton.getInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();
        Assert.isTrue(instance2 == instance1);
    }
}
