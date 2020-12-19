package cn.edu.ntu.common.api.test.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Data
@TableName("licence")
public class Licence {

  @TableId(value = "licence_id", type = IdType.AUTO)
  private Long licenceId;

  private Long organizationId;

  private String licenceType;

  private String productName;

  private Integer licenceMax;

  private Integer licenceAllocated;

  private String comment;
}
