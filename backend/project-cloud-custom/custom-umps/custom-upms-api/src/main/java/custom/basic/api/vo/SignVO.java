package custom.basic.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@Data
@JsonIgnoreProperties(
        value = {"insertedTime", "updatedTime", "isDeleted"},
        allowGetters = true)
public class SignVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private Long id;

    private Long memberId;
    private LocalDateTime signDate;
    private Integer amount;

    @ApiModelProperty(hidden = true)
    private LocalDateTime insertedTime;

    @ApiModelProperty(hidden = true)
    private LocalDateTime updatedTime;

    @ApiModelProperty(hidden = true)
    private Boolean isDeleted;
}
