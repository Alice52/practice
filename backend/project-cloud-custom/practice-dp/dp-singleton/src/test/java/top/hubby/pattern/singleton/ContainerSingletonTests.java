package top.hubby.pattern.singleton;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-09-15 10:54 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class ContainerSingletonTests {
    static final int CONCURRENT_COUNT = 10;

    @Test
    public void testLazyConcurrentSafe() {

        for (int i = 0; i < CONCURRENT_COUNT; i++) {
            new Thread(
                            () -> {
                                ContainerSingleton.registerInstance("zack", new Object());
                                Object instance = ContainerSingleton.getInstance("zack");
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
        Object instance1 = ContainerSingleton.getInstance("zack");
        Object instance2 = ContainerSingleton.getInstance("zack");
        Assert.isTrue(instance2 == instance1);
    }
}
