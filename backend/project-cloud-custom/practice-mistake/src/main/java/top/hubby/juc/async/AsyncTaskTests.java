package top.hubby.juc.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-10-20 9:53 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Api(tags = "Juc")
public class AsyncTaskTests {

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private static Callable<Integer> getAsyncTask(int i) {
        return () -> {
            TimeUnit.SECONDS.sleep(i);
            return i;
        };
    }

    private static Runnable executeAsyncTask(
            int i, CountDownLatch countDownLatch, List<Integer> result) {
        return () -> {
            try {
                TimeUnit.SECONDS.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (result) {
                result.add(i);
            }
            countDownLatch.countDown();
        };
    }

    @Test
    public void test1() {
        long begin = System.currentTimeMillis();
        List<Future<Integer>> futures =
                IntStream.rangeClosed(1, 4)
                        .mapToObj(i -> threadPool.submit(getAsyncTask(i)))
                        .collect(Collectors.toList());
        List<Integer> result =
                futures.stream()
                        .map(
                                future -> {
                                    try {
                                        return future.get(1, TimeUnit.SECONDS);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return -1;
                                    }
                                })
                        .collect(Collectors.toList());
        log.info("result {} took {} ms", result, System.currentTimeMillis() - begin);
    }

    @Test
    public void test2() {
        long begin = System.currentTimeMillis();
        int count = 4;
        List<Integer> result = new ArrayList<>(count);
        CountDownLatch countDownLatch = new CountDownLatch(count);
        IntStream.rangeClosed(1, count)
                .forEach(i -> threadPool.execute(executeAsyncTask(i, countDownLatch, result)));
        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("result {} took {} ms", result, System.currentTimeMillis() - begin);
    }
}
