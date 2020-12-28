package coding.ifelse.strategy.model.to;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
@Data
public class OrderDTO {

  private String code;
  private BigDecimal price;
  /**
   * 1. Normal Order<br>
   * 2. Group Order<br>
   * 3. Promotion Order
   */
  private String type;
}
