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
public class ConstantSingletonTests {
    static final int CONCURRENT_COUNT = 10;

    @Test
    public void testLazyConcurrentSafe() {

        for (int i = 0; i < CONCURRENT_COUNT; i++) {
            new Thread(
                            () -> {
                                ConstantSingleton instance = ConstantSingleton.getInstance();
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
        ConstantSingleton instance1 = ConstantSingleton.getInstance();
        ConstantSingleton instance2 = ConstantSingleton.getInstance();
        Assert.isTrue(instance2 == instance1);
    }
}
