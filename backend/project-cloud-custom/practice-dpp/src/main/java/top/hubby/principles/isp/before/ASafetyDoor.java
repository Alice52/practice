package top.hubby.principles.isp.before;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-09-17 3:58 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class ASafetyDoor implements SafetyDoor {
    @Override
    public void antiTheft() {
        log.info("防盗");
    }

    @Override
    public void fireProof() {
        log.info("防火");
    }

    @Override
    public void waterProof() {
        log.info("防水");
    }
}
