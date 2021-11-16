package top.hubby.serialize.de;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-29 1:34 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Data
public class APIResultWrong {
    private boolean success;
    private int code;

    public APIResultWrong() {}

    public APIResultWrong(int code) {
        this.code = code;
        if (code == 2000) {
            success = true;
        } else {
            success = false;
        }
    }
}
