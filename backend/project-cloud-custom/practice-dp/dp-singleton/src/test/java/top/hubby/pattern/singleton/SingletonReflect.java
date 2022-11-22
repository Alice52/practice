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
public class SingletonReflect {

    @SneakyThrows
    @Test
    public void testReflect() {
        Constructor<HungrySingleton> constructor = HungrySingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        HungrySingleton hungrySingleton = constructor.newInstance();
        HungrySingleton instance = HungrySingleton.getInstance();

        Assert.isFalse(instance == hungrySingleton);
    }

    @SneakyThrows
    @Test
    public void testReflectEnum() {
        Constructor<EnumSingleton> constructor = EnumSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        // throw exception； cannot call constructor of enum
        EnumSingleton instance1 = constructor.newInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();

        Assert.isFalse(instance2.equals(instance1));
    }
}
