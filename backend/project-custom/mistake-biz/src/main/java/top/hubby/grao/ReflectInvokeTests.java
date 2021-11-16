package top.hubby.grao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-11-12 1:54 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class ReflectInvokeTests {

    private void age(int age) {
        log.info("int age = {}", age);
    }

    private void age(Integer age) {
        log.info("Integer age = {}", age);
    }

    @Test
    public void wrong() throws Exception {
        getClass().getDeclaredMethod("age", Integer.TYPE).invoke(this, Integer.valueOf("36"));
    }

    @Test
    public void right() throws Exception {
        getClass().getDeclaredMethod("age", Integer.class).invoke(this, Integer.valueOf("36"));
        getClass().getDeclaredMethod("age", Integer.class).invoke(this, 36);
    }
}
