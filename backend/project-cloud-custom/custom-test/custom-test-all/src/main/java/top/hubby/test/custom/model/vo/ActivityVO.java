package top.hubby.test.custom.model.vo;

import top.hubby.test.custom.constants.enums.ActivityStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2021-04-09 10:19 <br>
 * @project integration <br>
 */
@Data
@NoArgsConstructor
public class ActivityVO implements Serializable {
    private Long id;

    private Long phaseId;

    private String activityCode;

    private String activityName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ActivityStatusEnum status;
}
