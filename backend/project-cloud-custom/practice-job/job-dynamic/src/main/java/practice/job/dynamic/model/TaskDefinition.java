package practice.job.dynamic.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import practice.job.dynamic.constants.enums.TaskStatus;

import java.time.LocalDateTime;

/**
 * @author asd <br>
 * @create 2021-12-23 2:45 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Data
@TableName("practice_job_task_definition")
public class TaskDefinition {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
    @EnumValue private TaskStatus status;
    private Integer delay;

    @TableField("next_run_at")
    private LocalDateTime nextRunAt;

    private String data;

    /**
     * 更新下一次运行时间
     *
     * @param now
     */
    public void updateNextRunTime(LocalDateTime now) {
        setNextRunAt(now.plusSeconds(delay));
    }

    /**
     * Double check，是否应该运行
     *
     * @param now
     * @return
     */
    public boolean shouldRun(LocalDateTime now) {
        return getStatus() == TaskStatus.ENABLE && nextRunAt != null && now.isAfter(nextRunAt);
    }
}
