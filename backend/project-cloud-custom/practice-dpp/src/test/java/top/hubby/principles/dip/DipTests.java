package top.hubby.principles.dip;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.hubby.principles.dip.after.cpu.Cpu;
import top.hubby.principles.dip.after.memory.Memory;

/**
 * @author asd <br>
 * @create 2021-09-17 3:41 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class DipTests {

    @Test
    public void testAfter() {
        Cpu cpu = new top.hubby.principles.dip.after.cpu.IntelCpu();
        Memory memory = new top.hubby.principles.dip.after.memory.KingstonMemory();
        top.hubby.principles.dip.after.Computer c = new top.hubby.principles.dip.after.Computer();
        c.setCpu(cpu);
        c.setMemory(memory);

        c.build();
    }

    @Test
    public void testBefore() {
        top.hubby.principles.dip.before.cpu.IntelCpu cpu =
                new top.hubby.principles.dip.before.cpu.IntelCpu();
        top.hubby.principles.dip.before.memory.KingstonMemory memory =
                new top.hubby.principles.dip.before.memory.KingstonMemory();
        top.hubby.principles.dip.before.Computer c = new top.hubby.principles.dip.before.Computer();
        c.setCpu(cpu);
        c.setMemory(memory);

        c.build();
    }
}
