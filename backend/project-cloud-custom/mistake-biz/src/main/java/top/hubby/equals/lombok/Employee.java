package top.hubby.equals.lombok;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-26 5:20 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Data
@EqualsAndHashCode(callSuper = true)
public class Employee extends Person {
    private String company;

    public Employee(String name, String identity, String company) {
        super(name, identity);
        this.company = company;
    }
}
