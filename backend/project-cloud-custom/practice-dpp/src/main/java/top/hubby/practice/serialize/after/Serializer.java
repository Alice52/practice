package top.hubby.practice.serialize.after;

import lombok.extern.slf4j.Slf4j;

/**
 * 此时违背了高内聚
 *
 * @author asd <br>
 * @create 2021-12-21 4:39 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class Serializer {
    public String serialize(Object object) {
        String serializedResult = "...";
        return serializedResult;
    }
}
