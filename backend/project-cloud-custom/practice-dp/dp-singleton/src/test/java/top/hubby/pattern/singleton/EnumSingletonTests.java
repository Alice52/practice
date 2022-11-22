package top.hubby.pattern.singleton;

import cn.hutool.core.lang.Assert;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * @author zack <br>
 * @create 2021-09-14<br>
 * @project pattern <br>
 */
@Slf4j
public class EnumSingletonTests {

    static final int CONCURRENT_COUNT = 10;

    @SneakyThrows
    @Test
    public void testReflect() {
        Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        // throw exception； cannot call constructor of enum
        EnumSingleton instance1 = constructor.newInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();

        Assert.isFalse(instance2.equals(instance1));
    }

    @Test
    public void testLazyConcurrentSafe() {

        for (int i = 0; i < CONCURRENT_COUNT; i++) {
            new Thread(
                            () -> {
                                EnumSingleton instance = EnumSingleton.INSTANCE;
                                log.info("instance: {}", instance);
                            })
                    .start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
    }

    @Test
    public void testLazy() {
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        Assert.isTrue(instance1 == instance2);
    }
}
