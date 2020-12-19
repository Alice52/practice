package cn.edu.ntu.common.api.test.model;

import lombok.Data;

/**
 * @author zack <br>
 * @create 2020/12/16 <br>
 * @project common-api <br>
 */
@Data
public class LicenceDTO {
  private String licenceId;

  private String organizationId;

  private String organizationName;

  private String contactName;

  private String contactEmail;

  private String contactPhone;

  private String licenceType;

  private String productName;

  private Integer licenceMax;

  private Integer licenceAllocated;

  private String comment;
}
