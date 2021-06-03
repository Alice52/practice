package common.redis.constants.enums;

import lombok.Getter;

/**
 * @author zack <br>
 * @create 2021-06-03 13:20 <br>
 * @project custom-test <br>
 */
@Getter
public enum LockLevel {
    GLOBAL(0),
    MEMBER(1);

    private final int code;

    LockLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
