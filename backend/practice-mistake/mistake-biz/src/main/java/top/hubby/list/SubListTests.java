package top.hubby.list;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author asd <br>
 * @create 2021-10-27 5:27 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class SubListTests {

    private static List<List<Integer>> data = new ArrayList<>();

    @Test
    public void wrong() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        List<Integer> subList = list.subList(1, 4);
        log.info("{}", subList);
        subList.remove(1);
        log.info("{}", list);
        list.add(0);
        try {
            subList.forEach(System.out::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void oom() {
        for (int i = 0; i < 1000; i++) {
            List<Integer> rawList =
                    IntStream.rangeClosed(1, 10000000).boxed().collect(Collectors.toList());
            // 出现 OOM 的原因是，循环中的 1000 个具有 10 万个元素的 List 始终得不到回收
            // 因为它始终被 subList 方法返回的 List 强引用
            data.add(rawList.subList(0, 1));
        }
    }

    @Test
    public void oomfix() {
        for (int i = 0; i < 1000; i++) {
            List<Integer> rawList =
                    IntStream.rangeClosed(1, 100000).boxed().collect(Collectors.toList());

            data.add(new ArrayList<>(rawList.subList(0, 1)));
        }
    }

    @Test
    public void right1() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        List<Integer> subList = new ArrayList<>(list.subList(1, 4));
        log.info("{}", subList);
        subList.remove(1);
        log.info("{}", list);
        list.add(0);
        subList.forEach(System.out::println);
    }

    @Test
    public void right2() {
        List<Integer> list = IntStream.rangeClosed(1, 10).boxed().collect(Collectors.toList());
        List<Integer> subList = list.stream().skip(1).limit(3).collect(Collectors.toList());
        log.info("{}", subList);
        subList.remove(1);
        log.info("{}", list);
        list.add(0);
        subList.forEach(System.out::println);
    }
}
