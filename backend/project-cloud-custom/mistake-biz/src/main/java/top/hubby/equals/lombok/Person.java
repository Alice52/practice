package top.hubby.equals.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-26 5:20 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Person {
    @EqualsAndHashCode.Exclude private String name;
    private String identity;
}
