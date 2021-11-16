package top.hubby.component;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import common.uid.generator.UidGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author asd <br>
 * @create 2021-10-22 10:33 AM <br>
 * @project swagger-3 <br>
 */
@Component
public class CustomerIdGenerator implements IdentifierGenerator {

    @Resource private UidGenerator uidGenerator;

    @Override
    public Number nextId(Object entity) {
        // 填充自己的Id生成器，
        return uidGenerator.getUID();
    }
}
