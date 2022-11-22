package top.hubby.practice.serialize.after.v2;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-12-21 4:43 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class UsageTest {

    @Test
    public void testSerial() {
        SerializableDemo serializable = new SerializableDemo(new Serialization());
        String serialize = serializable.getSerializer().serialize("");
    }

    @Test
    public void testDeserialize() {
        DeserializableDemo deserializable = new DeserializableDemo(new Serialization());
        Object deserialize = deserializable.getDeserializable().deserialize("");
    }
}

@Getter
class SerializableDemo {

    private Serializable serializer;

    public SerializableDemo(Serializable serializer) {
        this.serializer = serializer;
    }
}

@Getter
class DeserializableDemo {

    private Deserializable deserializable;

    public DeserializableDemo(Deserializable deserializable) {
        this.deserializable = deserializable;
    }
}
