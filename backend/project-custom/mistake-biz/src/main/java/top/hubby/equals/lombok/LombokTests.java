package top.hubby.equals.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-10-26 5:21 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class LombokTests {
    @Test
    public void testLombokEquals() {

        Employee employee1 = new Employee("zhuye", "001", "bkjk.com");
        Employee employee2 = new Employee("Joseph", "002", "bkjk.com");
        log.info("employee1.equals(employee2) ? {}", employee1.equals(employee2));
    }
}
