package top.hubby.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-11-12 4:11 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public abstract class SayService {
    List<String> data = new ArrayList<>();

    public void say() {
        data.add(
                IntStream.rangeClosed(1, 1000).mapToObj(__ -> "a").collect(Collectors.joining(""))
                        + UUID.randomUUID().toString());

        System.out.println(this.toString() + " size: " + data.size());

        log.info("{}, size: {}", this, data.size());
    }
}
