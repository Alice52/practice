package top.hubby.principles.isp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.hubby.principles.isp.before.ASafetyDoor;

/**
 * @author asd <br>
 * @create 2021-09-17 3:58 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class IspTests {

    @Test
    public void testBefore() {
        ASafetyDoor door = new ASafetyDoor();
        door.antiTheft();
        door.fireProof();
        door.waterProof();
    }
}
