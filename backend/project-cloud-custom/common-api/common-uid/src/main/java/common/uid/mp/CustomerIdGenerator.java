package common.uid.mp;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import common.uid.generator.UidGenerator;

/**
 * @author asd <br>
 * @create 2021-12-23 2:51 PM <br>
 * @project project-cloud-custom <br>
 */
public class CustomerIdGenerator implements IdentifierGenerator {

    @Resource private UidGenerator uidGenerator;

    @Override
    public Number nextId(Object entity) {
        // 填充自己的Id生成器，
        return uidGenerator.getUID();
    }
}
