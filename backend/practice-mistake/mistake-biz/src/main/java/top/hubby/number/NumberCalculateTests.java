package top.hubby.number;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author asd <br>
 * @create 2021-10-27 9:30 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class NumberCalculateTests {

    @Test
    public void testDouble() {

        log.info("0.1+0.2={}", 0.1 + 0.2);
        log.info("1.0-0.8={}", 1.0 - 0.8);
        log.info("4.015*100={}", 4.015 * 100);
        log.info("123.3/100={}", 123.3 / 100);
        double amount1 = 2.15;
        double amount2 = 1.10;

        log.info("2.15-1.10={}", amount1 - amount2);
    }

    @Test
    public void testDecimal() {
        log.info("{}", new BigDecimal(0.1).add(new BigDecimal(0.2)));
        log.info("{}", new BigDecimal("0.1").add(new BigDecimal("0.2")));
        log.info("{}", new BigDecimal(1.0).subtract(new BigDecimal(0.8)));
        log.info("{}", new BigDecimal("123.3").divide(new BigDecimal(100)));

        log.info("{}", new BigDecimal("4.015").multiply(new BigDecimal("100")));
        log.info("{}", new BigDecimal("4.015").multiply(new BigDecimal(100)));
        log.info("{}", new BigDecimal("4.015").multiply(BigDecimal.valueOf(100)));
        log.info("{}", new BigDecimal("4.015").multiply(new BigDecimal(Double.toString(100))));
    }

    @Test
    public void testDF() {
        double num1 = 3.35; // 3.3500000001
        float num2 = 3.35f; // 3.499999999
        log.info("{}", String.format("%.1f", num1)); // 四舍五入
        log.info("{}", String.format("%.1f", num2));

        double num12 = 3.35;
        float num22 = 3.35f;
        DecimalFormat format = new DecimalFormat("#.##");
        format.setRoundingMode(RoundingMode.DOWN);
        log.info("{}", format.format(num12));
        format.setRoundingMode(RoundingMode.DOWN);
        log.info("{}", format.format(num22));

        BigDecimal num13 = new BigDecimal("3.35");
        BigDecimal num23 = num13.setScale(1, BigDecimal.ROUND_DOWN);
        log.info("{}", num23);
        BigDecimal num33 = num13.setScale(1, BigDecimal.ROUND_HALF_UP);
        log.info("{}", num33);
    }

    @Test
    public void testEquals() {
        log.info("{}", new BigDecimal("1.0").equals(new BigDecimal("1")));
        log.info("{}", new BigDecimal("1.0").compareTo(new BigDecimal("1")) == 0);
    }

    @Test
    public void testHash() {
        Set<BigDecimal> hashSet = new HashSet<>();
        hashSet.add(new BigDecimal("1.0"));
        log.info("{}", hashSet.contains(new BigDecimal("1"))); // 返回false

        Set<BigDecimal> hashSet2 = new HashSet<>();
        hashSet2.add(new BigDecimal("1.0").stripTrailingZeros());
        log.info("{}", hashSet2.contains(new BigDecimal("1.000").stripTrailingZeros())); // 返回true

        Set<BigDecimal> treeSet = new TreeSet<>();
        treeSet.add(new BigDecimal("1.0"));
        log.info("{}", treeSet.contains(new BigDecimal("1"))); // 返回true
    }

    @Test
    public void testOverflow() {
        long l = Long.MAX_VALUE;
        log.info("{}", l + 1);
        log.info("{}", l + 1 == Long.MIN_VALUE);

        try {
            long l2 = Long.MAX_VALUE;
            log.info("{}", Math.addExact(l2, 1));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        BigInteger i = new BigInteger(String.valueOf(Long.MAX_VALUE));
        log.info("{}", i.add(BigInteger.ONE).toString());
        try {
            Long l3 = i.add(BigInteger.ONE).longValueExact();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void test() {
        log.info("{}", -1 & 15);
        log.info(Integer.toBinaryString(-15));

        ArrayDeque<Object> objects = new ArrayDeque<>();
        objects.addFirst("as");
        objects.addFirst("as2");
        objects.addLast("as45");

        /*
        -15
        0000 0000 0000 0000 0000 0000 0000 1111
        1111 1111 1111 1111 1111 1111 1111 0001

        -1 & 15
        1111 1111 1111 1111 1111 1111 1111 1111
        0000 0000 0000 0000 0000 0000 0000 1111
        ---------------------------------------
        0000 0000 0000 0000 0000 0000 0000 1111 = 15
        -----------
         */

    }
}
