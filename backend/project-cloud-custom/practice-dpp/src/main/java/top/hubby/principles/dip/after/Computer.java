package top.hubby.principles.dip.after;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.hubby.principles.dip.after.cpu.Cpu;
import top.hubby.principles.dip.after.memory.Memory;

/**
 * @author asd <br>
 * @create 2021-09-17 3:45 PM <br>
 * @project pattern <br>
 */
@Slf4j
@Data
public class Computer {
    private Cpu cpu;
    private Memory memory;
    // 运行计算机
    public void build() {
        log.info("Run computer with cpu[{}] and memory[{}]", cpu, memory);
    }
}
