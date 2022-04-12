package top.hubby.juc.concurrent.list.controller;

import cn.hutool.core.date.StopWatch;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author asd <br>
 * @create 2021-10-19 5:06 PM <br>
 * @project swagger-3 <br>
 */
@Api(tags = "Juc")
@Slf4j
@RestController
@RequestMapping("/juc/copy-on-write-list-misuse")
public class CopyOnWriteListMisuseController {

    @GetMapping("/write")
    public Map testWrite() {
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());

        StopWatch stopWatch = new StopWatch();
        int loopCount = 100_000;
        stopWatch.start("Write:copyOnWriteArrayList");
        IntStream.rangeClosed(1, loopCount)
                .parallel()
                .forEach(__ -> copyOnWriteArrayList.add(current().nextInt(loopCount)));
        stopWatch.stop();

        stopWatch.start("Write:synchronizedList");
        IntStream.rangeClosed(1, loopCount)
                .parallel()
                .forEach(__ -> synchronizedList.add(current().nextInt(loopCount)));
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());

        Map result = new HashMap();
        result.put("copyOnWriteArrayList", copyOnWriteArrayList.size());
        result.put("synchronizedList", synchronizedList.size());
        return result;
    }

    private void addAll(List<Integer> list) {
        list.addAll(IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList()));
    }

    @GetMapping("/read")
    public Map testRead() {
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        addAll(copyOnWriteArrayList);
        addAll(synchronizedList);

        StopWatch stopWatch = new StopWatch();
        int loopCount = 1000000;
        int count = copyOnWriteArrayList.size();

        stopWatch.start("Read:copyOnWriteArrayList");
        IntStream.rangeClosed(1, loopCount)
                .parallel()
                .forEach(__ -> copyOnWriteArrayList.get(current().nextInt(count)));
        stopWatch.stop();

        stopWatch.start("Read:synchronizedList");
        IntStream.range(0, loopCount)
                .parallel()
                .forEach(__ -> synchronizedList.get(current().nextInt(count)));
        stopWatch.stop();

        log.info(stopWatch.prettyPrint());

        Map result = new HashMap();
        result.put("copyOnWriteArrayList", copyOnWriteArrayList.size());
        result.put("synchronizedList", synchronizedList.size());
        return result;
    }
}
