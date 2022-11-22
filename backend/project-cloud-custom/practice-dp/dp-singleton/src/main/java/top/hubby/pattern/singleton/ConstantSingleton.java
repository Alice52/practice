package top.hubby.pattern.singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
@Slf4j
public class ConstantSingleton {

    public static /*final*/ ConstantSingleton instance = new ConstantSingleton();

    private ConstantSingleton() {}

    public static ConstantSingleton getInstance() {
        return instance;
    }
}
