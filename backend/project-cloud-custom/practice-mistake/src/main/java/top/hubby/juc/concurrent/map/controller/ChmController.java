package top.hubby.juc.concurrent.map.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import cn.hutool.core.date.StopWatch;
import cn.hutool.core.lang.Assert;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author asd <br>
 * @create 2021-10-19 4:11 PM <br>
 * @project swagger-3 <br>
 */
@Api(tags = "Juc")
@Slf4j
@RestController
@RequestMapping("/juc/chm")
public class ChmController {
    private static int LOOP_COUNT = 10000000;
    private static int THREAD_COUNT = 10;
    private static int ITEM_COUNT = 10;

    @GetMapping("/good")
    public String good() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("normaluse");
        Map<String, Long> normaluse = normaluse();
        stopWatch.stop();
        Assert.isTrue(normaluse.size() == ITEM_COUNT, "normaluse size error");
        Assert.isTrue(
                normaluse.values().stream().reduce(0L, Long::sum) == LOOP_COUNT,
                "normaluse count error");

        stopWatch.start("gooduse");
        Map<String, Long> gooduse = gooduse();
        stopWatch.stop();
        Assert.isTrue(gooduse.size() == ITEM_COUNT, "gooduse size error");
        Assert.isTrue(
                gooduse.values().stream().mapToLong(l -> l).reduce(0, Long::sum) == LOOP_COUNT,
                "gooduse count error");

        log.info(stopWatch.prettyPrint());
        return "OK";
    }

    @Deprecated
    private Map<String, Long> normaluse() throws InterruptedException {
        ConcurrentHashMap<String, Long> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(
                () ->
                        IntStream.rangeClosed(1, LOOP_COUNT)
                                .parallel()
                                .forEach(
                                        i -> {
                                            String key = "item" + current().nextInt(ITEM_COUNT);
                                            synchronized (freqs) {
                                                if (freqs.containsKey(key)) {
                                                    freqs.put(key, freqs.get(key) + 1);
                                                } else {
                                                    freqs.put(key, 1L);
                                                }
                                            }
                                        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return freqs;
    }

    private Map<String, Long> gooduse() throws InterruptedException {
        ConcurrentHashMap<String, LongAdder> freqs = new ConcurrentHashMap<>(ITEM_COUNT);
        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(
                () ->
                        IntStream.rangeClosed(1, LOOP_COUNT)
                                .parallel()
                                .forEach(
                                        i -> {
                                            String key = "item" + current().nextInt(ITEM_COUNT);
                                            freqs.computeIfAbsent(key, k -> new LongAdder())
                                                    .increment();
                                        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);
        return freqs.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().longValue()));
    }
}
