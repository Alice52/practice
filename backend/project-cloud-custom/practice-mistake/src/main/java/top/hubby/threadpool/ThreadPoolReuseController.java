package top.hubby.threadpool;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.swagger.annotations.Api;
import jodd.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asd <br>
 * @create 2021-10-20 5:05 PM <br>
 * @project swagger-3 <br>
 */
@Api(tags = "Pool")
@Slf4j
@RestController
@RequestMapping("/pool/re-use")
public class ThreadPoolReuseController {

    @GetMapping("/wrong")
    public String wrong() throws InterruptedException {
        // 这里会引起OOM, threadPool 对象回收不掉
        ThreadPoolExecutor threadPool = ThreadPoolHelper.getThreadPool();
        IntStream.rangeClosed(1, 10)
                .forEach(
                        i ->
                                threadPool.execute(
                                        () -> {
                                            String payload =
                                                    IntStream.rangeClosed(1, 1000000)
                                                                    .mapToObj(__ -> "a")
                                                                    .collect(Collectors.joining(""))
                                                            + UUID.randomUUID().toString();
                                            try {
                                                TimeUnit.SECONDS.sleep(1);
                                            } catch (InterruptedException e) {
                                            }
                                            log.debug(payload);
                                        }));
        return "OK";
    }

    static class ThreadPoolHelper {
        private static ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(
                        10,
                        50,
                        2,
                        TimeUnit.SECONDS,
                        new ArrayBlockingQueue<>(1000),
                        new ThreadFactoryBuilder().setNameFormat("demo-threadpool-%d").get());

        // 这里会引起OOM, threadPool 对象回收不掉, 其中的 core 线程也不会回收, 所以会有很多线程
        public static ThreadPoolExecutor getThreadPool() {
            return (ThreadPoolExecutor) Executors.newCachedThreadPool();
        }

        static ThreadPoolExecutor getRightThreadPool() {
            return threadPoolExecutor;
        }
    }
}
