package top.hubby.coding.elseif.interfaces;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public interface IType {
    /**
     * Get type id by input.
     *
     * @return
     */
    default Integer getTypeId() {
        return 0;
    }
}
