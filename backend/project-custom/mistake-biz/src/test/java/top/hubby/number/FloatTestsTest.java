package top.hubby.number;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-11-01 10:09 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class FloatTestsTest {

    @Test
    public void testFloat() {
        int intBits = Float.floatToIntBits(1.234f);
        log.info("float to int bits: {}", intBits);
        float f = Float.intBitsToFloat(intBits);
        log.info("int bits to float: {}", f);

        intBits = intBits + 1;
        float f1 = Float.intBitsToFloat(intBits);
        log.info("[error] int bits to float: {}", f1);
    }
}
