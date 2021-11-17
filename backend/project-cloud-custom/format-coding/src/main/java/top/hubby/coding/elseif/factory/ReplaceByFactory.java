package top.hubby.coding.elseif.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public class ReplaceByFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ReplaceByFactory.class);

    private static final TypeFactory factory = new TypeFactory();

    public static Integer getTypeId(String type) {

        Integer typeId = 0;
        if ("Name".equals(type)) {
            typeId = 1;
        } else if ("Age".equals(type)) {
            typeId = 2;
        } else if ("Address".equals(type)) {
            typeId = 3;
        }

        return typeId;
    }

    public static Integer getTypeIdAfter(String type) {
        return factory.getType(type).getTypeId();
    }

    public static void main(String[] args) {
        LOG.info("{}", getTypeId("Name"));
        LOG.info("{}", getTypeIdAfter("Name"));
    }
}
