package top.hubby.cpu;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * @author asd <br>
 * @create 2021-11-16 9:15 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Component
public class HighCpu {

    /** thread -i 3000 -n 5 */
    @SneakyThrows
    // @PostConstruct
    public void cpu() {
        while (true) {}
    }
}
