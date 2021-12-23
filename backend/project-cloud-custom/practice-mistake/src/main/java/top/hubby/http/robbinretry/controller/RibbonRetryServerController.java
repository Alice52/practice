package top.hubby.http.robbinretry.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * use by feign.
 *
 * @author asd <br>
 * @create 2021-10-22 4:28 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@RestController
@Api(tags = "Http")
@RequestMapping("/http/ribbonretry/server")
public class RibbonRetryServerController {
    @GetMapping("/sms")
    public void sendSmsWrong(
            @RequestParam("mobile") String mobile,
            @RequestParam("message") String message,
            HttpServletRequest request)
            throws InterruptedException {
        log.info("{} is called, {}=>{}", request.getRequestURL().toString(), mobile, message);
        TimeUnit.SECONDS.sleep(2);
    }

    @PostMapping("/sms")
    public void sendSmsRight(
            @RequestParam("mobile") String mobile,
            @RequestParam("message") String message,
            HttpServletRequest request)
            throws InterruptedException {
        log.info("{} is called, {}=>{}", request.getRequestURL().toString(), mobile, message);
        TimeUnit.SECONDS.sleep(2);
    }
}
