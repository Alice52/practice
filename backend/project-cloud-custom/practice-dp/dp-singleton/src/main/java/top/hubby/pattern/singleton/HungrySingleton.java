package top.hubby.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

import java.io.ObjectStreamException;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
@Slf4j
public class HungrySingleton {
    //    private static HungrySingleton instance;
    //    static {
    //        instance = new HungrySingleton();
    //    }

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
        if (instance != null) {
            throw new RuntimeException("singleton instance is not null");
        }
    }

    public static HungrySingleton getInstance() {
        return instance;
    }

    private Object readResolve() throws ObjectStreamException {
        return getInstance();
    }
}
