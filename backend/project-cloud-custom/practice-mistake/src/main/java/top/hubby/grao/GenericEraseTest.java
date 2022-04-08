package top.hubby.grao;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-11-12 1:56 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class GenericEraseTest {

    @Test
    public void wrong1() {
        Child1 child1 = new Child1();
        Arrays.stream(child1.getClass().getMethods())
                .filter(method -> method.getName().equals("setValue"))
                .forEach(
                        method -> {
                            try {
                                method.invoke(child1, "test");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
        log.info("{}", child1.toString());
    }

    @Test
    @Deprecated
    public void right0() {
        Child1 child1 = new Child1();
        Arrays.stream(child1.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("setValue"))
                .forEach(
                        method -> {
                            try {
                                method.invoke(child1, "test");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
        log.info("{}", child1.toString());
    }

    @Test
    public void wrong3() {
        Child2 child2 = new Child2();
        List<Method> collect =
                Arrays.stream(child2.getClass().getDeclaredMethods()).collect(Collectors.toList());

        collect.stream()
                .filter(method -> method.getName().equals("setValue"))
                .forEach(
                        method -> {
                            try {
                                method.invoke(child2, "test");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
        log.info("{}", child2.toString());
    }

    @SneakyThrows
    @Test
    public void right() {
        Child2 child2 = new Child2();

        List<Method> collect =
                Arrays.stream(child2.getClass().getMethods()).collect(Collectors.toList());

        collect.stream()
                .filter(method -> method.getName().equals("setValue") && !method.isBridge())
                .findFirst()
                .ifPresent(
                        method -> {
                            try {
                                method.invoke(child2, "test");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
        log.info("{}", child2.toString());

        TimeUnit.HOURS.sleep(1);
    }
}

@Slf4j
class Parent<T> {

    AtomicInteger updateCount = new AtomicInteger();

    private T value;

    @Override
    public String toString() {
        return String.format("value: %s updateCount: %d", value, updateCount.get());
    }

    public void setValue(T value) {
        log.info("{}", "Parent.setValue called");
        this.value = value;
        updateCount.incrementAndGet();
    }
}

@Slf4j
class Child1 extends Parent {

    /**
     * 这个方法不是重载方法
     *
     * @param value
     */
    public void setValue(String value) {
        log.info("{}", "Child1.setValue called");
        super.setValue(value);
    }
}

@Slf4j
class Child2 extends Parent<String> {

    @Override
    public void setValue(String value) {
        log.info("{}", "Child2.setValue called");
        super.setValue(value);
    }
}
