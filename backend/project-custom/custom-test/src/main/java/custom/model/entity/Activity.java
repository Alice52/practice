package custom.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import custom.constants.enums.ActivityStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2021-04-09 10:20 <br>
 * @project integration <br>
 */
@Data
@TableName("boot_cache_all_star_activity")
public class Activity extends BaseEntity<Activity> {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 阶段Id */
    private Long phaseId;

    /** 活动编码 */
    private String activityCode;

    /** 活动名称 */
    private String activityName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    /** 活动状态 */
    private ActivityStatusEnum status;
}
