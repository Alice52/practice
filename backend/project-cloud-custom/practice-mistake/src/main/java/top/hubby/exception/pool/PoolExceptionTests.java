package top.hubby.exception.pool;

import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author asd <br>
 * @create 2021-10-28 3:10 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class PoolExceptionTests {
    static Thread.UncaughtExceptionHandler func0 =
            (thread, throwable) -> log.error("ThreadPool {} got exception", thread, throwable);

    static {
        Thread.setDefaultUncaughtExceptionHandler(func0);
    }

    @Test
    public void execute() throws InterruptedException {

        String prefix = "test";
        ExecutorService threadPool =
                Executors.newFixedThreadPool(
                        1,
                        new ThreadFactoryBuilder()
                                .setNameFormat(prefix + "%d")
                                .setUncaughtExceptionHandler(func0)
                                .get());

        IntStream.rangeClosed(1, 10)
                .forEach(
                        i ->
                                threadPool.execute(
                                        () -> {
                                            if (i == 5) {
                                                throw new RuntimeException("error");
                                            }
                                            log.info("I'm done : {}", i);
                                        }));

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    @Test
    public void submit() throws InterruptedException {

        String prefix = "test";
        ExecutorService threadPool =
                Executors.newFixedThreadPool(
                        1, new ThreadFactoryBuilder().setNameFormat(prefix + "%d").get());
        IntStream.rangeClosed(1, 10)
                .forEach(
                        i ->
                                threadPool.submit(
                                        () -> {
                                            if (i == 5) {
                                                throw new RuntimeException("error");
                                            }
                                            log.info("I'm done : {}", i);
                                        }));

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    @Test
    public void submitRight() throws InterruptedException {

        String prefix = "test";
        ExecutorService threadPool =
                Executors.newFixedThreadPool(
                        1, new ThreadFactoryBuilder().setNameFormat(prefix + "%d").get());

        List<Future> tasks =
                IntStream.rangeClosed(1, 10)
                        .mapToObj(
                                i ->
                                        threadPool.submit(
                                                () -> {
                                                    if (i == 5) {
                                                        throw new RuntimeException("error");
                                                    }
                                                    log.info("I'm done : {}", i);
                                                }))
                        .collect(Collectors.toList());

        tasks.forEach(
                task -> {
                    try {
                        task.get();
                    } catch (Exception e) {
                        log.error("Got exception", e);
                    }
                });
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }
}
