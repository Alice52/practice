package top.hubby.coding.elseif.enums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.hubby.coding.elseif.enums.constants.TypeEnum;
import top.hubby.coding.elseif.enums.constants.TypeImplEnum;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public class ReplaceByEnum {

    private static final Logger LOG = LoggerFactory.getLogger(ReplaceByEnum.class);

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
        return TypeEnum.valueOf(type.toUpperCase()).getTypeId();
    }

    public static Integer getTypeIdAfter2(String type) {
        return TypeImplEnum.valueOf(type.toUpperCase()).getTypeId();
    }

    public static void main(String[] args) {
        LOG.info("{}", getTypeId("Name"));
        LOG.info("{}", getTypeIdAfter("Name"));
        LOG.info("{}", getTypeIdAfter2("Name"));
    }
}
