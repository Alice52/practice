package top.hubby.principles.isp.after;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-09-17 4:00 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class ASafetyDoor implements AntiTheft, Fireproof, Waterproof {
    @Override
    public void antiTheft() {
        log.info("防盗");
    }

    @Override
    public void fireproof() {
        log.info("防火");
    }

    @Override
    public void waterproof() {
        log.info("防水");
    }
}
