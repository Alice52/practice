package top.hubby.principles.srp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.hubby.principles.srp.after.Aquatic;
import top.hubby.principles.srp.after.Terrestrial;
import top.hubby.principles.srp.before.Animal;

/**
 * @author asd <br>
 * @create 2021-09-17 2:55 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class SrpTests {

    @Test
    public void testBefore() {
        Animal animal = new Animal();
        animal.breathe("牛");
        animal.breathe("羊");
        animal.breathe("猪");
    }

    @Test
    public void testAfter() {
        Terrestrial terrestrial = new Terrestrial();
        terrestrial.breathe("牛");
        terrestrial.breathe("羊");
        terrestrial.breathe("猪");

        Aquatic aquatic = new Aquatic();
        aquatic.breathe("鱼");
    }
}
