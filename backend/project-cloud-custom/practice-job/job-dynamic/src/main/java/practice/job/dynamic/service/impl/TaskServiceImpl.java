package practice.job.dynamic.service.impl;

import lombok.extern.slf4j.Slf4j;
import practice.job.dynamic.service.TaskService;
import practice.job.dynamic.task.Task;

import org.springframework.stereotype.Service;

/**
 * @author asd <br>
 * @create 2021-12-23 5:58 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public void runTask(Task task) {
        log.info("run Task {}", task);
    }
}
