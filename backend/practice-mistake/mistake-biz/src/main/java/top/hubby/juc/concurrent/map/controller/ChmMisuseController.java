package top.hubby.juc.concurrent.map.controller;

import common.uid.generator.UidGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * @author asd <br>
 * @create 2021-10-19 6:07 PM <br>
 * @project swagger-3 <br>
 */
@RestController
@RequestMapping("/juc/chm-misuse")
@Slf4j
public class ChmMisuseController {

    private static int THREAD_COUNT = 10;
    private static int ITEM_COUNT = 1000;
    @Resource private UidGenerator uidGenerator;

    private ConcurrentHashMap<String, Long> getData(int count) {
        return LongStream.rangeClosed(1, count)
                .boxed()
                .collect(
                        Collectors.toConcurrentMap(
                                i -> String.valueOf(uidGenerator.getUID()),
                                Function.identity(),
                                (o1, o2) -> o1,
                                ConcurrentHashMap::new));
    }

    @GetMapping("/wrong")
    public String wrong() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        log.info("init size:{}", concurrentHashMap.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(
                () ->
                        IntStream.rangeClosed(1, 10)
                                .parallel()
                                .forEach(
                                        i -> {
                                            int gap = ITEM_COUNT - concurrentHashMap.size();
                                            log.info("gap size:{}", gap);
                                            concurrentHashMap.putAll(getData(gap));
                                        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);

        log.info("finish size:{}", concurrentHashMap.size());
        return "OK";
    }

    @GetMapping("/right")
    public String right() throws InterruptedException {
        ConcurrentHashMap<String, Long> concurrentHashMap = getData(ITEM_COUNT - 100);
        log.info("init size:{}", concurrentHashMap.size());

        ForkJoinPool forkJoinPool = new ForkJoinPool(THREAD_COUNT);
        forkJoinPool.execute(
                () ->
                        IntStream.rangeClosed(1, 10)
                                .parallel()
                                .forEach(
                                        i -> {
                                            synchronized (concurrentHashMap) {
                                                int gap = ITEM_COUNT - concurrentHashMap.size();
                                                log.info("gap size:{}", gap);
                                                concurrentHashMap.putAll(getData(gap));
                                            }
                                        }));
        forkJoinPool.shutdown();
        forkJoinPool.awaitTermination(1, TimeUnit.HOURS);

        log.info("finish size:{}", concurrentHashMap.size());
        return "OK";
    }
}
