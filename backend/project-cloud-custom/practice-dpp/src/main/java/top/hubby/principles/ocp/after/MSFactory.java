package top.hubby.principles.ocp.after;

import lombok.extern.slf4j.Slf4j;
import top.hubby.principles.ocp.Computer;
import top.hubby.principles.ocp.Surface;

/**
 * @author asd <br>
 * @create 2021-09-17 3:02 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class MSFactory implements ComputerFactory {
    @Override
    public Computer produceComputer() {
        return new Surface();
    }
}
