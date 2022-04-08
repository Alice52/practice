package top.hubby.test.custom.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hubby.test.custom.constants.enums.ActivityStatusEnum;

/**
 * @author zack <br>
 * @create 2021-04-09 10:20 <br>
 * @project integration <br>
 */
@Data
@NoArgsConstructor
public class ActivityDTO implements Serializable {

    @ApiModelProperty(hidden = true)
    private Long id;

    private Long phaseId;

    private String activityCode;

    private String activityName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ActivityStatusEnum status;
}
