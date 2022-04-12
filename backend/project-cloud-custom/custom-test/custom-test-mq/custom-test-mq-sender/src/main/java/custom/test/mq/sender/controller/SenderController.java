package custom.test.mq.sender.controller;

import common.core.util.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hubby.mq.sender.SenderService;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2022-04-08 20:38 <br>
 * @project project-cloud-custom <br>
 */
@RestController
@Slf4j
@RequestMapping("/mq")
@Api(value = "MQ-Sender")
public class SenderController {

    @Resource private SenderService sender;

    @PostMapping("/msg")
    public R<Boolean> sendMessage(@RequestBody String msg) {

        sender.convertAndSend(msg);

        return new R<>(true);
    }
}
