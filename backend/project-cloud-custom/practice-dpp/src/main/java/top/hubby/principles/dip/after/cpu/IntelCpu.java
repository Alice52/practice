package top.hubby.principles.dip.after.cpu;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-09-17 3:46 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class IntelCpu implements Cpu {
    @Override
    public void run() {
        log.info("使用Intel处理器");
    }
}
