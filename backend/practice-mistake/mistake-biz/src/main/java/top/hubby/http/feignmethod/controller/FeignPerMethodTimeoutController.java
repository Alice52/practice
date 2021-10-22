package top.hubby.http.feignmethod.controller;

import feign.Request;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hubby.http.feignmethod.client.TimeoutClient;

import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-10-22 4:57 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@RestController
@Api(tags = "Http")
@RequestMapping("/http/feignmethod/timeout")
public class FeignPerMethodTimeoutController {

    @Autowired private TimeoutClient client;

    @GetMapping("/test")
    public void test() {
        String result1 = client.method1(new Request.Options(1000, 2500));
        String result2 = client.method2(new Request.Options(1000, 3500));
        log.info("result1:{} result2:{}", result1, result2);
    }

    @GetMapping("/method1")
    public String method1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return "method1";
    }

    @GetMapping("/method2")
    public String method2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return "method1";
    }
}
