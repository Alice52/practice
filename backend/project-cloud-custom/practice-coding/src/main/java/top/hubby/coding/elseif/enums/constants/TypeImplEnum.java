package top.hubby.coding.elseif.enums.constants;

import top.hubby.coding.elseif.interfaces.IType;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public enum TypeImplEnum implements IType {
    NAME {
        @Override
        public Integer getTypeId() {
            return 1;
        }
    },
    AGE {
        @Override
        public Integer getTypeId() {
            return 2;
        }
    },
    ADDRESS {
        @Override
        public Integer getTypeId() {
            return 3;
        }
    };
}
