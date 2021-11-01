package top.hubby.datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import static java.time.temporal.ChronoField.DAY_OF_MONTH;
import static java.time.temporal.ChronoField.MONTH_OF_YEAR;

/**
 * @author asd <br>
 * @create 2021-11-01 3:45 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class DateTimeCalTests {

    public static Boolean isFamilyBirthday(TemporalAccessor date) {
        int month = date.get(MONTH_OF_YEAR);
        int day = date.get(DAY_OF_MONTH);

        if (month == Month.FEBRUARY.getValue() && day == 17) {
            return Boolean.TRUE;
        }
        if (month == Month.SEPTEMBER.getValue() && day == 21) {
            return Boolean.TRUE;
        }
        if (month == Month.MAY.getValue() && day == 22) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Test
    public void wrong1() {
        log.info("{}", "wrong1");
        Date today = new Date();
        Date nextMonth = new Date(today.getTime() + 30 * 1000 * 60 * 60 * 24);
        log.info("{}", today);
        log.info("{}", nextMonth);
    }

    @Test
    public void wrong1fix() {
        log.info(
                "{}",
                30 * 1000 * 60 * 60 * 24 + " " + (30L * 1000 * 60 * 60 * 24 > Integer.MAX_VALUE));
        log.info("{}", "wrong1fix");
        Date today = new Date();
        Date nextMonth = new Date(today.getTime() + 30L * 1000 * 60 * 60 * 24);
        log.info("{}", today);
        log.info("{}", nextMonth);
    }

    @Test
    public void right() {
        log.info("{}", "right");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, 30);
        log.info("{}", c.getTime());
    }

    @Test
    public void better() {
        log.info("{}", "better");
        LocalDateTime localDateTime = LocalDateTime.now();
        log.info("{}", localDateTime.plusDays(30));
    }

    @Test
    public void test() {
        log.info("{}", "//测试操作日期");
        log.info(
                "{}",
                LocalDate.now()
                        .minus(Period.ofDays(1))
                        .plus(1, ChronoUnit.DAYS)
                        .minusMonths(1)
                        .plus(Period.ofMonths(1)));

        log.info("{}", "//计算日期差");
        LocalDate today = LocalDate.of(2019, 12, 12);
        LocalDate specifyDate = LocalDate.of(2019, 10, 1);
        log.info("{}", Period.between(specifyDate, today).getDays());
        log.info("{}", Period.between(specifyDate, today));
        log.info("{}", ChronoUnit.DAYS.between(specifyDate, today));

        log.info("{}", "//本月的第一天");
        log.info("{}", LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));

        log.info("{}", "//今年的程序员日");
        log.info("{}", LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).plusDays(255));

        log.info("{}", "//今天之前的一个周六");
        log.info("{}", LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.SATURDAY)));

        log.info("{}", "//本月最后一个工作日");
        log.info("{}", LocalDate.now().with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY)));

        log.info("{}", "//自定义逻辑");
        log.info(
                "{}",
                LocalDate.now()
                        .with(
                                temporal ->
                                        temporal.plus(
                                                ThreadLocalRandom.current().nextInt(100),
                                                ChronoUnit.DAYS)));

        log.info("{}", "//查询是否是今天要举办生日");
        log.info("{}", LocalDate.now().query(DateTimeCalTests::isFamilyBirthday));
    }
}
