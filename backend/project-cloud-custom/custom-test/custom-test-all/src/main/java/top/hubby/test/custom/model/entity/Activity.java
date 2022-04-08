package top.hubby.test.custom.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import common.database.model.entity.BaseEntity;
import lombok.Data;
import top.hubby.test.custom.constants.enums.ActivityStatusEnum;

/**
 * @author zack <br>
 * @create 2021-04-09 10:20 <br>
 * @project integration <br>
 */
@Data
@TableName("boot_cache_all_star_activity")
public class Activity extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long phaseId;

    private String activityCode;

    private String activityName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ActivityStatusEnum status;
}
