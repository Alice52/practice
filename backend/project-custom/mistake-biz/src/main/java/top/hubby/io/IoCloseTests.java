package top.hubby.io;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * @author asd <br>
 * @create 2021-10-29 9:52 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class IoCloseTests {

    @Test
    public void readLargeFileWrong() throws IOException {
        log.info("lines {}", Files.readAllLines(Paths.get("large.txt")).size());
    }

    @Test
    @Deprecated
    public void readLargeFileRight() throws IOException {
        AtomicLong atomicLong = new AtomicLong();
        Files.lines(Paths.get("large.txt")).forEach(line -> atomicLong.incrementAndGet());
        log.info("lines {}", atomicLong.get());
    }

    @Test
    public void linesTest() throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("read 20 lines");
        log.info(
                "lines {}",
                Files.lines(Paths.get("large.txt")).limit(20).collect(Collectors.toList()).size());
        stopWatch.stop();
        stopWatch.start("read 200 lines");
        log.info(
                "lines {}",
                Files.lines(Paths.get("large.txt")).limit(200).collect(Collectors.toList()).size());
        stopWatch.stop();
        log.info(stopWatch.prettyPrint());
    }

    @Test
    public void init() throws IOException {

        String payload =
                IntStream.rangeClosed(1, 1000).mapToObj(__ -> "a").collect(Collectors.joining(""))
                        + UUID.randomUUID().toString();
        Files.deleteIfExists(Paths.get("demo.txt"));
        IntStream.rangeClosed(1, 10)
                .forEach(
                        __ -> {
                            try {
                                Files.write(
                                        Paths.get("demo.txt"),
                                        IntStream.rangeClosed(1, 50)
                                                .mapToObj(i -> payload)
                                                .collect(Collectors.toList()),
                                        UTF_8,
                                        CREATE,
                                        APPEND);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
    }

    @Test
    public void wrong() {
        // ps aux | grep CommonMistakesApplication
        // lsof -p 63937
        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 1000000)
                .forEach(
                        i -> {
                            try {
                                Files.lines(Paths.get("demo.txt"))
                                        .forEach(line -> longAdder.increment());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
        log.info("total : {}", longAdder.longValue());
    }

    @Test
    public void right() {
        // https://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html
        LongAdder longAdder = new LongAdder();
        IntStream.rangeClosed(1, 1000000)
                .forEach(
                        i -> {
                            try (Stream<String> lines = Files.lines(Paths.get("demo.txt"))) {
                                lines.forEach(line -> longAdder.increment());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
        log.info("total : {}", longAdder.longValue());
    }
}
