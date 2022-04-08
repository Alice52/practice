package top.hubby.equals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asd <br>
 * @create 2021-10-26 4:57 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@RestController
@Api(tags = "Equals")
@RequestMapping("/equal")
public class IntAndStringEqualController {

    List<String> list = new ArrayList<>();

    @GetMapping("/str/intern")
    public int internperformance(
            @RequestParam(value = "size", defaultValue = "10000000") int size) {
        // -XX:+PrintStringTableStatistics
        // -XX:StringTableSize=10000000
        long begin = System.currentTimeMillis();
        list =
                IntStream.rangeClosed(1, size)
                        .mapToObj(i -> String.valueOf(i).intern())
                        .collect(Collectors.toList());

        log.info("size:{} took:{}", size, System.currentTimeMillis() - begin);
        return list.size();
    }
}
