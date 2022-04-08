package top.hubby.threadpool;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.swagger.annotations.Api;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.rangeClosed;

/**
 * @author asd <br>
 * @create 2021-10-20 2:15 PM <br>
 * @project swagger-3 <br>
 */
@Api(tags = "Pool")
@Slf4j
@RestController
@RequestMapping("/pool/oom")
public class ThreadPoolOOMController {

    private static ScheduledFuture<?> printStats(ThreadPoolExecutor threadPool) {
        return Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(
                        () -> {
                            log.info("=========================");
                            log.info("Pool Size: {}", threadPool.getPoolSize());
                            log.info("Active Threads: {}", threadPool.getActiveCount());
                            log.info(
                                    "Number of Tasks Completed: {}",
                                    threadPool.getCompletedTaskCount());
                            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());

                            log.info("=========================");
                        },
                        0,
                        1,
                        TimeUnit.SECONDS);
    }

    @GetMapping("/oom1")
    public void oom1() throws InterruptedException {

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        printStats(threadPool);
        for (int i = 0; i < 100000000; i++) {
            threadPool.execute(
                    () -> {
                        String payload =
                                rangeClosed(1, 1000000).mapToObj(__ -> "a").collect(joining(""))
                                        + UUID.randomUUID().toString();
                        sleep(TimeUnit.HOURS, 1);
                        log.info(payload);
                    });
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
    }

    @GetMapping("/oom2")
    public void oom2() throws InterruptedException {

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        printStats(threadPool);
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
                        new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").get(),
                        new ThreadPoolExecutor.AbortPolicy());
        // threadPool.allowCoreThreadTimeOut(true);
        // threadPool.prestartAllCoreThreads()
        ScheduledFuture<?> scheduledFuture = printStats(threadPool);
        rangeClosed(1, 20)
                .forEach(
                        i -> {
                            sleep(TimeUnit.SECONDS, 1);
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

        TimeUnit.SECONDS.sleep(60);
        scheduledFuture.cancel(true);
        return atomicInteger.intValue();
    }

    @GetMapping("/better")
    public int better() throws InterruptedException {
        // 这里开始是激进线程池的实现
        BlockingQueue<Runnable> queue =
                new LinkedBlockingQueue<Runnable>(10) {
                    @Override
                    public boolean offer(Runnable e) {
                        // 先返回false，造成队列满的假象，让线程池优先扩容
                        return false;
                    }
                };

        RejectedExecutionHandler handler =
                (r, executor) -> {
                    try {
                        // 等出现拒绝后再加入队列,
                        // 如果希望队列满了阻塞线程而不是抛出异常，那么可以注释掉下面三行代码，修改为 executor.getQueue().put(r);
                        if (!executor.getQueue().offer(r, 0, TimeUnit.SECONDS)) {
                            throw new RejectedExecutionException(
                                    "ThreadPool queue full, failed to offer " + r.toString());
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                };
        ThreadPoolExecutor threadPool =
                new ThreadPoolExecutor(
                        2,
                        5,
                        5,
                        TimeUnit.SECONDS,
                        queue,
                        new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").get(),
                        handler);
        // 激进线程池实现结束

        ScheduledFuture<?> scheduledFuture = printStats(threadPool);
        // 每秒提交一个任务，每个任务耗时10秒执行完成，一共提交20个任务

        // 任务编号计数器
        AtomicInteger atomicInteger = new AtomicInteger();

        rangeClosed(1, 20)
                .forEach(
                        i -> {
                            sleep(TimeUnit.SECONDS, 1);
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

        TimeUnit.SECONDS.sleep(60);
        scheduledFuture.cancel(true);
        return atomicInteger.intValue();
    }

    private void sleep(TimeUnit seconds, int i) {
        try {
            seconds.sleep(i);
        } catch (InterruptedException e) {
        }
    }
}
