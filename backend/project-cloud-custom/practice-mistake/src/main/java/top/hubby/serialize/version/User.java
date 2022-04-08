package top.hubby.serialize.version;

import java.io.Serializable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-29 11:03 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 6634621353121349665L;
    private String name;
    private int age;
}
