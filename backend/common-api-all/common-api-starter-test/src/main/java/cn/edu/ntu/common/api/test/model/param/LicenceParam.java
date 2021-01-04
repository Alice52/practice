package cn.edu.ntu.common.api.test.model.param;

import cn.edu.ntu.common.api.response.model.QueryParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LicenceParam extends QueryParam {

  @NotBlank(message = "licence type cannot be empty.")
  private String licenceType;
}
