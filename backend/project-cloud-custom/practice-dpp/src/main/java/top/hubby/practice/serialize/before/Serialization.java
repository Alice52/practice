package top.hubby.practice.serialize.before;

import lombok.extern.slf4j.Slf4j;

/**
 * 在某些类只需要序列化或反序列化功能时, 这么设计虽然符合高内聚, 但是却违背了迪米特原则[基于最小接口而非最大实现编程]
 *
 * @author asd <br>
 * @create 2021-12-21 4:34 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class Serialization {

    public String serialize(Object object) {
        String serializedResult = "...";
        // ...
        return serializedResult;
    }

    public Object deserialize(String str) {
        Object deserializedResult = "...";
        // ...
        return deserializedResult;
    }
}
