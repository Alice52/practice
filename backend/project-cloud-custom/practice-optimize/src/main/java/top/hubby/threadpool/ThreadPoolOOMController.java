package top.hubby.threadpool;

import common.core.executor.RadicalThreadPoolExecutor;
import common.core.util.pool.PoolMonitorUtil;
import io.swagger.annotations.Api;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.rangeClosed;

/**
 * @author asd <br>
 * @create 2021-10-20 2:15 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Api(tags = "Pool")
@RestController
@RequestMapping("/pool/oom")
public class ThreadPoolOOMController {

    static final ThreadFactory factory =
            new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").setPriority(4).get();

    @GetMapping("/oom1")
    public void oom1() throws InterruptedException {

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        PoolMonitorUtil.printStatsFixedRate(threadPool, 1);
        for (int i = 0; i < 10000; i++) {
            threadPool.execute(
                    () -> {
                        String payload =
                                rangeClosed(1, 11).mapToObj(__ -> "a").collect(joining(""))
                                        + UUID.randomUUID().toString();
                        sleep(TimeUnit.SECONDS, 1);
                        log.info(payload);
                    });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    @GetMapping("/oom2")
    public void oom2() throws InterruptedException {

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        PoolMonitorUtil.printStatsFixedRate(threadPool, 1);
        for (int i = 0; i < 100000000; i++) {
            threadPool.execute(
                    () -> {
                        String payload = UUID.randomUUID().toString();
                        sleep(TimeUnit.HOURS, 1);
                        log.info(payload);
                    });
        }
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    @GetMapping("/right")
    public int right() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(
                        2,
                        5,
                        5,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(10),
                        factory,
                        new ThreadPoolExecutor.AbortPolicy());

        ScheduledFuture<?> scheduledFuture = PoolMonitorUtil.printStatsFixedRate(threadPool, 1);

        submitTask(threadPool, atomicInteger);

        TimeUnit.SECONDS.sleep(60);
        scheduledFuture.cancel(true);
        return atomicInteger.intValue();
    }

    @GetMapping("/better")
    public int better() throws InterruptedException {
        // 这里开始是激进线程池的实现
        // BlockingQueue<Runnable> queue = new RadicalBlockingQueue<>(10);
        // ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
        //             2, 5, 5, TimeUnit.SECONDS, queue, factory, new CallerBlocksPolicy());
        ThreadPoolExecutor threadPool =
                RadicalThreadPoolExecutor.newRadicalThreadPoolExecutor(2, 5, 5);

        threadPool.allowCoreThreadTimeOut(true);
        threadPool.prestartAllCoreThreads();

        ScheduledFuture<?> scheduledFuture = PoolMonitorUtil.printStatsFixedRate(threadPool, 1);
        // 每秒提交一个任务，每个任务耗时10秒执行完成，一共提交20个任务

        // 任务编号计数器
        AtomicInteger atomicInteger = new AtomicInteger();
        submitTask(threadPool, atomicInteger);
        TimeUnit.SECONDS.sleep(60);
        scheduledFuture.cancel(true);
        return atomicInteger.intValue();
    }

    private static void submitTask(ThreadPoolExecutor threadPool, AtomicInteger atomicInteger) {
        rangeClosed(1, 200)
                .forEach(
                        i -> {
                            int id = atomicInteger.incrementAndGet();
                            try {
                                threadPool.submit(
                                        () -> {
                                            log.info("{} started", id);
                                            sleep(TimeUnit.SECONDS, 10);
                                            log.info("{} finished", id);
                                        });
                            } catch (Exception ex) {
                                log.error("error submitting task {}", id, ex);
                                atomicInteger.decrementAndGet();
                            }
                        });
    }

    @SneakyThrows
    private static void sleep(TimeUnit seconds, int i) {
        seconds.sleep(i);
    }
}
