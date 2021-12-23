package top.hubby.coding.elseif.oop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.hubby.coding.elseif.interfaces.IType;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public class ReplaceByOop {

    private static final Logger LOG = LoggerFactory.getLogger(ReplaceByOop.class);

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

    public static Integer getTypeIdAfter(String type)
            throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        IType iType =
                (IType) Class.forName("top.hubby.coding.elseif.interfaces." + type).newInstance();

        return iType.getTypeId();
    }

    public static void main(String[] args) throws Exception {
        LOG.info("{}", getTypeId("Name"));
        LOG.info("{}", getTypeIdAfter("Name"));
    }
}
