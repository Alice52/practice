package top.hubby.http.robbinretry.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author asd <br>
 * @create 2021-10-22 4:27 PM <br>
 * @project swagger-3 <br>
 */
@FeignClient(name = "SmsClient")
public interface SmsClient {

    @GetMapping("/http/ribbonretry/server/sms")
    void sendSmsWrong(
            @RequestParam("mobile") String mobile, @RequestParam("message") String message);

    @PostMapping("/http/ribbonretry/server/sms")
    void sendSmsRight(
            @RequestParam("mobile") String mobile, @RequestParam("message") String message);
}
