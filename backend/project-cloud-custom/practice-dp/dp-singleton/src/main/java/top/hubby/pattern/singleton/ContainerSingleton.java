package top.hubby.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author asd <br>
 * @create 2021-09-15 10:54 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class ContainerSingleton {
    private static Map<String, Object> objMap = new HashMap<>();

    private ContainerSingleton() {}

    public static void registerInstance(String key, Object instance) {
        objMap.putIfAbsent(key, instance);
    }

    public static Object getInstance(String key) {
        return objMap.get(key);
    }
}
