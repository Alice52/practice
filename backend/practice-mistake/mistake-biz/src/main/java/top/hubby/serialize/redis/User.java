package top.hubby.serialize.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author asd <br/>
 * @create 2021-10-29 1:39 PM <br/>
 * @project swagger-3 <br/>
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
class User implements Serializable {
    private String name;
    private int age;
}