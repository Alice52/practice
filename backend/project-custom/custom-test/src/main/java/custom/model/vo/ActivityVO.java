package custom.model.vo;

import cn.hutool.core.bean.BeanUtil;
import custom.constants.enums.ActivityStatusEnum;
import custom.model.entity.Activity;
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

    public ActivityVO(Activity allStarActivity) {
        BeanUtil.copyProperties(allStarActivity, this, "status");
    }
}
