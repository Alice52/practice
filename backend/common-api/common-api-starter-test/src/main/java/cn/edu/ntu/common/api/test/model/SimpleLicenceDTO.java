package cn.edu.ntu.common.api.test.model;

import lombok.Data;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Data
public class SimpleLicenceDTO {
  private String licenceId;

  private String organizationId;

  private String licenceType;

  private String productName;

  private Integer licenceMax;

  private Integer licenceAllocated;

  private String comment;
}
