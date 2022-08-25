package top.hubby.component;

import common.uid.generator.UidGenerator;
import common.uid.mp.CustomerIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author asd <br>
 * @create 2021-11-17 1:05 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Component
public class MpAssignIdGenerator extends CustomerIdGenerator {
    public MpAssignIdGenerator(UidGenerator uidGenerator) {
        super(uidGenerator);
    }
}
