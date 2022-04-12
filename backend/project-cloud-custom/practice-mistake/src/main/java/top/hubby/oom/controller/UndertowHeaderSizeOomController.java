package top.hubby.oom.controller;

import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-11-11 10:26 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Api(tags = "OOM")
@RestController
@RequestMapping("/undertow/oom")
public class UndertowHeaderSizeOomController {

    @SneakyThrows
    @GetMapping("/big-header")
    public String bigHeader(@RequestParam("pa") String pa) {

        TimeUnit.SECONDS.sleep(3);

        return "OK";
    }
}
