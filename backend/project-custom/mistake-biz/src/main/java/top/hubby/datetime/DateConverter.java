package top.hubby.datetime;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author asd <br>
 * @create 2021-11-01 4:04 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public final class DateConverter {

    public static void main(String[] args) {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
