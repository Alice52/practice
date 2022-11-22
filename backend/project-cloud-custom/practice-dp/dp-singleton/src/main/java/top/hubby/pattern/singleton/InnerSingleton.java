package top.hubby.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
@Slf4j
public class InnerSingleton {
    private InnerSingleton() {}

    public static InnerSingleton getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static /*final*/ InnerSingleton instance = new InnerSingleton();
    }
}
