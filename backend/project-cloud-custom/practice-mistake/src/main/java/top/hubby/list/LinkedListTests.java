package top.hubby.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

import org.springframework.util.StopWatch;

/**
 * @author asd <br>
 * @create 2021-10-27 5:51 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class LinkedListTests {
    public static void main(String[] args) {
        int elementCount = 100000;
        int loopCount = 100000;

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("linkedListGet");
        linkedListGet(elementCount, loopCount);
        stopWatch.stop();

        stopWatch.start("arrayListGet");
        arrayListGet(elementCount, loopCount);
        stopWatch.stop();
        log.info("{}", stopWatch.prettyPrint());

        StopWatch stopWatch2 = new StopWatch();
        stopWatch2.start("linkedListAdd");
        linkedListAdd(elementCount, loopCount);
        stopWatch2.stop();

        stopWatch2.start("arrayListAdd");
        arrayListAdd(elementCount, loopCount);
        stopWatch2.stop();

        log.info("{}", stopWatch2.prettyPrint());
    }

    private static void linkedListGet(int elementCount, int loopCount) {
        List<Integer> list =
                IntStream.rangeClosed(1, elementCount)
                        .boxed()
                        .collect(Collectors.toCollection(LinkedList::new));
        IntStream.rangeClosed(1, loopCount)
                .forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
    }

    private static void arrayListGet(int elementCount, int loopCount) {
        List<Integer> list =
                IntStream.rangeClosed(1, elementCount)
                        .boxed()
                        .collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1, loopCount)
                .forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
    }

    private static void linkedListAdd(int elementCount, int loopCount) {
        List<Integer> list =
                IntStream.rangeClosed(1, elementCount)
                        .boxed()
                        .collect(Collectors.toCollection(LinkedList::new));
        // 慢的原因: add(index, e) 需要先找到元素, 之后才能执行O(1)的插入
        IntStream.rangeClosed(1, loopCount)
                .forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount), 1));
    }

    private static void arrayListAdd(int elementCount, int loopCount) {
        List<Integer> list =
                IntStream.rangeClosed(1, elementCount)
                        .boxed()
                        .collect(Collectors.toCollection(ArrayList::new));
        IntStream.rangeClosed(1, loopCount)
                .forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount), 1));
    }
}
