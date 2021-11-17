package top.hubby.juc.tl.controller;

import common.uid.generator.UidGenerator;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.rangeClosed;

/**
 * @author asd <br>
 * @create 2021-10-19 2:09 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@RestController
@Api(tags = "Juc")
@RequestMapping("/juc/tl-memory-leak")
public class TlMemoryLeakController {
    private static final ThreadLocal<List<String>> tl = ThreadLocal.withInitial(() -> null);
    @Resource private UidGenerator uidGenerator;

    @GetMapping("/wrong")
    public void wrong() {
        List<String> d =
                rangeClosed(1, 10)
                        .mapToObj(
                                i ->
                                        rangeClosed(1, 1000000)
                                                        .mapToObj(__ -> "a")
                                                        .collect(joining(""))
                                                + uidGenerator.getUID())
                        .collect(Collectors.toList());
        tl.set(d);
    }
}
