package top.hubby.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-10-27 6:06 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class ListRemoveTests {

    @Test
    public void removeByIndex() {
        List<Integer> list =
                IntStream.rangeClosed(1, 10)
                        .boxed()
                        .collect(Collectors.toCollection(ArrayList::new));
        log.info("{}", list.remove(2));
        log.info("{}", list);
    }

    @Test
    public void removeByValue() {
        List<Integer> list =
                IntStream.rangeClosed(1, 10)
                        .boxed()
                        .collect(Collectors.toCollection(ArrayList::new));
        log.info("{}", list.remove(Integer.valueOf(10)));
        log.info("{}", list);
    }

    @Test
    public void forEachRemoveWrong() {
        List<String> list =
                IntStream.rangeClosed(1, 10)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.toCollection(ArrayList::new));
        for (String i : list) {
            if ("2".equals(i)) {
                // ConcurrentModificationException
                list.remove(i);
            }
        }
        log.info("{}", list);
    }

    @Test
    public void forEachRemoveRight() {
        List<String> list =
                IntStream.rangeClosed(1, 10)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.toCollection(ArrayList::new));
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            String next = iterator.next();
            if ("2".equals(next)) {
                iterator.remove();
            }
        }
        log.info("{}", list);
    }

    @Test
    public void forEachRemoveRight2() {
        List<String> list =
                IntStream.rangeClosed(1, 10)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.toCollection(ArrayList::new));
        list.removeIf("2"::equals);
        log.info("{}", list);
    }
}
