package practice.job.dynamic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import practice.job.dynamic.constants.enums.TaskStatus;
import practice.job.dynamic.model.TaskDefinition;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author asd <br>
 * @create 2021-12-23 3:02 PM <br>
 * @project project-cloud-custom <br>
 */
public interface TaskDefinitionService extends IService<TaskDefinition> {
    List<TaskDefinition> getByStatusAndNextRunAtLessThan(TaskStatus enable, LocalDateTime now);
}
