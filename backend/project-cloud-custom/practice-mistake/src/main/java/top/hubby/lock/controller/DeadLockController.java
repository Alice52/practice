package top.hubby.lock.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.swagger.annotations.Api;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.IntStream.rangeClosed;

/**
 * @author asd <br>
 * @create 2021-10-20 10:39 AM <br>
 * @project swagger-3 <br>
 */
@Api(tags = "Lock")
@Slf4j
@RestController
@RequestMapping("/lock/dead")
public class DeadLockController {

    private ConcurrentHashMap<String, Item> items = new ConcurrentHashMap<>();

    public DeadLockController() {
        IntStream.range(0, 10).forEach(i -> items.put("item" + i, new Item("item" + i)));
    }

    @SneakyThrows
    private boolean createOrder(List<Item> order) {
        List<ReentrantLock> locks = new ArrayList<>();

        for (Item item : order) {
            if (item.lock.tryLock(10, TimeUnit.SECONDS)) {
                locks.add(item.lock);
            } else {
                locks.forEach(ReentrantLock::unlock);
                return false;
            }
        }
        try {
            order.forEach(item -> item.remaining--);
        } finally {
            locks.forEach(ReentrantLock::unlock);
        }
        return true;
    }

    private List<Item> createCart() {
        return rangeClosed(1, 3)
                .mapToObj(i -> "item" + ThreadLocalRandom.current().nextInt(items.size()))
                .map(name -> items.get(name))
                .collect(Collectors.toList());
    }

    @GetMapping("/wrong")
    public long wrong() {
        long begin = System.currentTimeMillis();
        long success =
                rangeClosed(1, 100)
                        .parallel()
                        .mapToObj(
                                i -> {
                                    List<Item> cart = createCart();
                                    return createOrder(cart);
                                })
                        .filter(result -> result)
                        .count();
        log.info(
                "success:{} totalRemaining:{} took:{}ms items:{}",
                success,
                items.values().stream().map(item -> item.remaining).reduce(0, Integer::sum),
                System.currentTimeMillis() - begin,
                items);
        return success;
    }

    @GetMapping("/right")
    public long right() {
        long begin = System.currentTimeMillis();
        long success =
                rangeClosed(1, 100)
                        .parallel()
                        .mapToObj(
                                i -> {
                                    List<Item> cart =
                                            createCart().stream()
                                                    .sorted(Comparator.comparing(Item::getName))
                                                    .collect(Collectors.toList());
                                    return createOrder(cart);
                                })
                        .filter(result -> result)
                        .count();
        log.info(
                "success:{} totalRemaining:{} took:{}ms items:{}",
                success,
                items.values().stream().map(item -> item.remaining).reduce(0, Integer::sum),
                System.currentTimeMillis() - begin,
                items);
        return success;
    }

    @Data
    @RequiredArgsConstructor
    static class Item {
        final String name;
        int remaining = 1000;
        @ToString.Exclude ReentrantLock lock = new ReentrantLock();
    }
}
