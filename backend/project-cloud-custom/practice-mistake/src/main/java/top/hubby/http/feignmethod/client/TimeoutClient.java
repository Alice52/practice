package top.hubby.http.feignmethod.client;

import feign.Request;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author asd <br>
 * @create 2021-10-22 4:56 PM <br>
 * @project swagger-3 <br>
 */
@FeignClient(name = "TimeoutClient")
public interface TimeoutClient {
    @GetMapping("/http/feignmethod/timeout/method1")
    String method1(Request.Options options);

    @GetMapping("/http/feignmethod/timeout/method2")
    String method2(Request.Options options);
}
