package practice.job.dynamic.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import practice.job.dynamic.constants.enums.TaskStatus;
import practice.job.dynamic.mapper.TaskDefinitionMapper;
import practice.job.dynamic.model.TaskDefinition;
import practice.job.dynamic.service.TaskDefinitionService;

import org.springframework.stereotype.Service;

/**
 * @author asd <br>
 * @create 2021-12-23 3:03 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Service
public class TaskDefinitionServiceImpl extends ServiceImpl<TaskDefinitionMapper, TaskDefinition>
        implements TaskDefinitionService {
    @Override
    public List<TaskDefinition> getByStatusAndNextRunAtLessThan(
            TaskStatus enable, LocalDateTime now) {

        val wrapper =
                Wrappers.<TaskDefinition>lambdaQuery()
                        .eq(TaskDefinition::getStatus, enable)
                        .lt(TaskDefinition::getNextRunAt, now);

        return this.list(wrapper);
    }
}
