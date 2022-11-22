package top.hubby.practice.serialize.after;

import lombok.extern.slf4j.Slf4j;

/**
 * 此时违背了高内聚
 *
 * @author asd <br>
 * @create 2021-12-21 4:40 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class Deserializer {
    public Object deserialize(String str) {
        Object deserializedResult = "...";
        return deserializedResult;
    }
}
