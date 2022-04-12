package custom.test.mq.sender.controller;

import common.core.util.R;
import common.uid.generator.CachedUidGenerator;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hubby.mq.constants.enums.EventStatus;
import top.hubby.mq.sender.SenderService;
import top.hubby.mq.service.DtxEventService;

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
    @Resource
    private CachedUidGenerator uidGenerator;
    @Resource
    private DtxEventService dtxService;
    @Resource private SenderService sender;

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/msg")
    public R<Boolean> sendMessage(@RequestBody String msg) {

        long uid = uidGenerator.getUID();

        dtxService.createEvent(uid, "test-event", "{body--}", EventStatus.NEW);




        sender.convertAndSend(msg);

        return new R<>(true);
    }
}
