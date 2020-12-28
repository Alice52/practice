package coding.ifelse.factory;

import coding.ifelse.factory.impls.Address;
import coding.ifelse.factory.impls.Age;
import coding.ifelse.factory.impls.Name;
import coding.ifelse.interfaces.IType;

import java.util.HashMap;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public class TypeFactory {

  static HashMap<String, IType> typeMaps =
      new HashMap<String, IType>() {
        {
          put("Name", new Name());
          put("Age", new Age());
          put("Address", new Address());
        }
      };

  public IType getType(String type) {
    return typeMaps.get(type);
  }

  public Integer getTypeId(String type) {
    return typeMaps.get(type).getTypeId();
  }
}
