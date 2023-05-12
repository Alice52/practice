package top.hubby.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.util.StopWatch;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author asd <br>
 * @create 2021-10-27 5:48 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class ListMapTests {

    public static void main(String[] args) throws InterruptedException {

        int elementCount = 1000000;
        int loopCount = 1000;
        StopWatch stopWatch = new StopWatch();

        stopWatch.start("listSearch");
        Object list = listSearch(elementCount, loopCount);
        // log.info("{}", jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize(list));
        stopWatch.stop();

        stopWatch.start("mapSearch");
        Object map = mapSearch(elementCount, loopCount);
        stopWatch.stop();

        // log.info("{}", jdk.nashorn.internal.ir.debug.ObjectSizeCalculator.getObjectSize(map));
        log.info("{}", stopWatch.prettyPrint());
        TimeUnit.HOURS.sleep(1);
    }

    private static Object listSearch(int elementCount, int loopCount) {
        List<Order> list =
                IntStream.rangeClosed(1, elementCount)
                        .mapToObj(Order::new)
                        .collect(Collectors.toList());
        IntStream.rangeClosed(1, loopCount)
                .forEach(
                        i -> {
                            int search = ThreadLocalRandom.current().nextInt(elementCount);
                            Order result =
                                    list.stream()
                                            .filter(order -> order.getOrderId() == search)
                                            .findFirst()
                                            .orElse(null);
                            Assert.assertTrue(result != null && result.getOrderId() == search);
                        });
        return list;
    }

    private static Object mapSearch(int elementCount, int loopCount) {
        Map<Integer, Order> map =
                IntStream.rangeClosed(1, elementCount)
                        .boxed()
                        .collect(Collectors.toMap(Function.identity(), Order::new));
        IntStream.rangeClosed(1, loopCount)
                .forEach(
                        i -> {
                            int search = ThreadLocalRandom.current().nextInt(elementCount);
                            Order result = map.get(search);
                            Assert.assertTrue(result != null && result.getOrderId() == search);
                        });
        return map;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Order {
        private int orderId;
    }
}
