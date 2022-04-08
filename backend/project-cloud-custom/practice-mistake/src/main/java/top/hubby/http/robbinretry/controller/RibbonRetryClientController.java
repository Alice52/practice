package top.hubby.http.robbinretry.controller;

import java.util.UUID;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import top.hubby.http.robbinretry.feign.SmsClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asd <br>
 * @create 2021-10-22 4:30 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@RestController
@Api(tags = "Http")
@RequestMapping("/http/ribbonretry/client")
public class RibbonRetryClientController {
    @Autowired private SmsClient smsClient;

    /**
     * application-common.yml 中配置
     *
     * <pre>
     *    1. robbin get 默认会 retry
     * </pre>
     *
     * @return
     */
    @GetMapping("/wrong")
    public String wrong() {
        log.info("client is called");
        try {
            smsClient.sendSmsWrong("13600000000", UUID.randomUUID().toString());
        } catch (Exception ex) {
            log.error("send sms failed : {}", ex.getMessage());
        }
        return "done";
    }

    @GetMapping("/right")
    public String right() {
        try {
            smsClient.sendSmsRight("13600000000", UUID.randomUUID().toString());
        } catch (Exception ex) {
            log.error("send sms failed : {}", ex.getMessage());
        }
        return "done";
    }
}
