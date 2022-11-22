package top.hubby.principles.srp.before;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-09-17 2:51 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class Animal {
    public void breathe(String animal) {
        log.info("{} 呼吸空气", animal);
    }
}
