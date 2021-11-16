package top.hubby.serialize.de;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-29 1:33 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Data
public class APIResultRight {
    private boolean success;
    private int code;

    public APIResultRight() {}

    @JsonCreator
    public APIResultRight(@JsonProperty("code") int code) {
        this.code = code;
        if (code == 2000) {
            success = true;
        } else {
            success = false;
        }
    }
}
