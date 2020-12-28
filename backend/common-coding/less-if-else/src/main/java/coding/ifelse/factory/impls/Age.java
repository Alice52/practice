package coding.ifelse.factory.impls;

import coding.ifelse.interfaces.IType;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public class Age implements IType {

  @Override
  public Integer getTypeId() {
    return 3;
  }
}
