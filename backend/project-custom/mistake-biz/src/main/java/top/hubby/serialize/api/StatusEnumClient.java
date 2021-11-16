package top.hubby.serialize.api;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-29 3:42 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Getter
@AllArgsConstructor
public enum StatusEnumClient {
    CREATED(1, "已创建"),
    PAID(2, "已支付"),
    DELIVERED(3, "已送到"),
    FINISHED(4, "已完成"),
    @JsonEnumDefaultValue
    UNKNOWN(-1, "未知");

    @JsonValue private final int status;
    private final String desc;

    /*
    @JsonCreator
    public static StatusEnumServer parse(Object o) {
        return Arrays.stream(StatusEnumServer.values())
                .filter(value -> o.equals(value.status))
                .findFirst()
                .orElse(null);
    }
     */
}
