package top.hubby.practice.serialize.after.v2;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-12-21 4:42 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class Serialization implements Serializable, Deserializable {
    @Override
    public String serialize(Object object) {
        String serializedResult = "...";
        return serializedResult;
    }

    @Override
    public Object deserialize(String str) {
        Object deserializedResult = "...";
        return deserializedResult;
    }
}
