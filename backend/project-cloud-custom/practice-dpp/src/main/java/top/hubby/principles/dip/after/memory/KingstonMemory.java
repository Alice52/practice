package top.hubby.principles.dip.after.memory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-09-17 3:47 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class KingstonMemory implements Memory {

    @Override
    public void save() {
        log.info("使用金士顿内存条");
    }
}
