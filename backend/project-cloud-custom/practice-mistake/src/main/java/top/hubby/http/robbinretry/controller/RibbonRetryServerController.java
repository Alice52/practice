package top.hubby.http.robbinretry.controller;

import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
