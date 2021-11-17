package top.hubby.juc.concurrent.map.controller;

import common.uid.generator.UidGenerator;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-10-20 9:24 AM <br>
 * @project swagger-3 <br>
 */
@Api(tags = "Juc")
@Slf4j
@RestController
@RequestMapping("/juc/chm-misuse")
public class MapController {

    @Resource private UidGenerator uidGenerator;

    @GetMapping("/method")
    public void testMapMethod() {
        test(new HashMap<>());
        test(new ConcurrentHashMap<>());
    }

    private void test(Map<String, String> map) {
        log.info("class : {}", map.getClass().getName());
        try {
            log.info("putIfAbsent null value : {}", map.putIfAbsent("test1", null));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.info("test containsKey after putIfAbsent : {}", map.containsKey("test1"));
        log.info("computeIfAbsent null value : {}", map.computeIfAbsent("test2", k -> null));
        log.info("test containsKey after computeIfAbsent : {}", map.containsKey("test2"));
        log.info("putIfAbsent non-null value : {}", map.putIfAbsent("test3", "test3"));
        log.info("computeIfAbsent non-null value : {}", map.computeIfAbsent("test4", k -> "test4"));
        log.info("putIfAbsent expensive value : {}", map.putIfAbsent("test4", getValue()));
        log.info(
                "computeIfAbsent expensive value : {}",
                map.computeIfAbsent("test4", k -> getValue()));
    }

    private String getValue() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.valueOf(uidGenerator.getUID());
    }
}
