package top.hubby.principles.ocp.before;

import lombok.extern.slf4j.Slf4j;
import top.hubby.principles.ocp.Computer;
import top.hubby.principles.ocp.Macbook;
import top.hubby.principles.ocp.Surface;

/**
 * @author asd <br>
 * @create 2021-09-17 2:59 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class ComputerFactory {
    public Computer produceComputer(String type) {
        Computer c = null;
        if (type.equals("macbook")) {
            c = new Macbook();
        } else if (type.equals("surface")) {
            c = new Surface();
        }
        return c;
    }
}
