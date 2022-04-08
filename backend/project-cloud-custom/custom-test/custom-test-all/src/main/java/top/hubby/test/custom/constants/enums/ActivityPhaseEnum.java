package top.hubby.test.custom.constants.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zack <br>
 * @create 2021-04-09 10:28 <br>
 * @project integration <br>
 */
@Getter
@AllArgsConstructor
public enum ActivityPhaseEnum {
    WARM_UP,
    START_UP,
    RESTAURANT_COMPETITION,
    ONLINE_PK,
    SUMMARY;

    public static Boolean contains(String phase) {

        return Arrays.stream(ActivityPhaseEnum.values())
                .map(Enum::name)
                .collect(Collectors.toList())
                .contains(phase);
    }
}
