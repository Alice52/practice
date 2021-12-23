package top.hubby.coding.elseif.enums.constants;

import lombok.Getter;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Getter
public enum TypeEnum {
    NAME(1),
    AGE(2),
    ADDRESS(3);

    private Integer typeId;

    TypeEnum(Integer typeId) {
        this.typeId = typeId;
    }
}
