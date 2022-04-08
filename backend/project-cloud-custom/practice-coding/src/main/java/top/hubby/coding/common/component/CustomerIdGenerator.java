package top.hubby.coding.common.component;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import common.uid.generator.UidGenerator;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * @author asd <br>
 * @create 2021-11-17 1:05 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Component
public class CustomerIdGenerator implements IdentifierGenerator {

    @Resource private UidGenerator uidGenerator;

    @Override
    public Number nextId(Object entity) {
        // 填充自己的Id生成器，
        return uidGenerator.getUID();
    }
}
