package top.hubby.lock.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asd <br>
 * @create 2021-10-20 10:24 AM <br>
 * @project swagger-3 <br>
 */
@Api(tags = "Lock")
@Slf4j
@RestController
@RequestMapping("/lock/granularity")
public class LockGranularityController {

    private List<Integer> data = new ArrayList<>();

    private void slow() {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
        }
    }

    @GetMapping("/wrong")
    public int wrong() {
        long begin = System.currentTimeMillis();
        IntStream.rangeClosed(1, 1000)
                .parallel()
                .forEach(
                        i -> {
                            synchronized (this) {
                                slow();
                                data.add(i);
                            }
                        });
        log.info("took:{}", System.currentTimeMillis() - begin);
        return data.size();
    }

    @GetMapping("/right")
    public int right() {
        long begin = System.currentTimeMillis();
        IntStream.rangeClosed(1, 1000)
                .parallel()
                .forEach(
                        i -> {
                            slow();
                            synchronized (data) {
                                data.add(i);
                            }
                        });
        log.info("took:{}", System.currentTimeMillis() - begin);
        return data.size();
    }
}
