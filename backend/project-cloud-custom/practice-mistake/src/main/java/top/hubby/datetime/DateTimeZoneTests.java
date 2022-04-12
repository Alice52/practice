package top.hubby.datetime;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author asd <br>
 * @create 2021-11-01 3:39 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class DateTimeZoneTests {

    @Test
    public void test() {
        log.info("{}", "test");
        log.info("{}", new Date(0));
        // log.info("{}", TimeZone.getDefault().getID() + ":" +
        // TimeZone.getDefault().getRawOffset()/3600/1000);
        // ZoneId.getAvailableZoneIds().forEach(id -> log.info("{}", String.format("%s:%s", id,
        // ZonedDateTime.now(ZoneId.of(id)))));
    }

    @Test
    public void wrong1() throws ParseException {
        log.info("{}", "wrong1");
        String stringDate = "2020-01-02 22:00:00";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = inputFormat.parse(stringDate);
        log.info("{}", date1 + ":" + date1.getTime());
        inputFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        Date date2 = inputFormat.parse(stringDate);
        log.info("{}", date2 + ":" + date2.getTime());
    }

    @Test
    public void wrong2() throws ParseException {
        log.info("{}", "wrong2");
        String stringDate = "2020-01-02 22:00:00";
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = inputFormat.parse(stringDate);
        log.info("{}", new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss Z]").format(date));
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
        log.info("{}", new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss Z]").format(date));
    }

    @Test
    public void right() {
        log.info("{}", "right");

        String stringDate = "2020-01-02 22:00:00";
        ZoneId timeZoneSH = ZoneId.of("Asia/Shanghai");
        ZoneId timeZoneNY = ZoneId.of("America/New_York");
        ZoneId timeZoneJST = ZoneOffset.ofHours(9);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        ZonedDateTime date =
                ZonedDateTime.of(LocalDateTime.parse(stringDate, dateTimeFormatter), timeZoneJST);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");

        log.info("{}", timeZoneSH.getId() + outputFormat.withZone(timeZoneSH).format(date));
        log.info("{}", timeZoneNY.getId() + outputFormat.withZone(timeZoneNY).format(date));
        log.info("{}", timeZoneJST.getId() + outputFormat.withZone(timeZoneJST).format(date));
    }
}
