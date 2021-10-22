package top.hubby.http.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static org.apache.http.impl.client.HttpClients.custom;

/**
 * @author asd <br>
 * @create 2021-10-22 4:10 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@RestController
@Api(tags = "Http")
@RequestMapping("/http/routelimit")
public class RouteLimitController {
    static CloseableHttpClient httpClient1;
    static CloseableHttpClient httpClient2;

    static {
        // default setMaxConnPerRoute=2
        httpClient1 =
                custom().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
        httpClient2 = custom().setMaxConnPerRoute(10).setMaxConnTotal(20).build();

        Runnable voidFunc0 =
                () -> {
                    try {
                        httpClient1.close();
                        httpClient2.close();
                    } catch (IOException ex) {
                    }
                };
        Runtime.getRuntime().addShutdownHook(new Thread(voidFunc0));
    }

    private int sendRequest(int count, Supplier<CloseableHttpClient> client)
            throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        long begin = System.currentTimeMillis();

        Runnable func0 =
                () -> {
                    try (val response =
                            client.get()
                                    .execute(
                                            new HttpGet(
                                                    "http://127.0.0.1:8080/http/routelimit/server"))) {
                        atomicInteger.addAndGet(
                                parseInt(EntityUtils.toString(response.getEntity())));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                };

        IntStream.rangeClosed(1, count).forEach(i -> threadPool.execute(func0));
        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.HOURS);
        log.info("发送 {} 次请求，耗时 {} ms", atomicInteger.get(), System.currentTimeMillis() - begin);
        return atomicInteger.get();
    }

    @GetMapping("/wrong")
    public int wrong(@RequestParam(value = "count", defaultValue = "10") int count)
            throws InterruptedException {
        return sendRequest(count, () -> httpClient1);
    }

    @GetMapping("/right")
    public int right(@RequestParam(value = "count", defaultValue = "10") int count)
            throws InterruptedException {
        return sendRequest(count, () -> httpClient2);
    }

    @GetMapping("/server")
    public int server() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return 1;
    }
}
