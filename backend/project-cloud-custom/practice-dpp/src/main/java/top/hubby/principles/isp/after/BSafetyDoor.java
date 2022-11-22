package top.hubby.principles.isp.after;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-09-17 4:01 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class BSafetyDoor implements AntiTheft, Fireproof {
    @Override
    public void antiTheft() {
        log.info("防盗");
    }

    @Override
    public void fireproof() {
        log.info("防火");
    }
}
