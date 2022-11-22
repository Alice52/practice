package top.hubby.principles.dip.before;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.hubby.principles.dip.before.cpu.IntelCpu;
import top.hubby.principles.dip.before.memory.KingstonMemory;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
@Slf4j
@Data
public class Computer {
    private IntelCpu cpu;
    private KingstonMemory memory;

    public void build() {
        log.info("Run computer with cpu[{}] and memory[{}]", cpu, memory);
    }
}
