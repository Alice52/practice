package practice.job.dynamic.schedule.v1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import practice.job.dynamic.service.TaskService;
import practice.job.dynamic.task.Task;

/**
 * @author asd <br>
 * @create 2021-12-23 2:07 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Service
public class ScheduleService {
    @Autowired private TaskService taskService;

    /**
     *
     *
     * <pre>
     *    1. cron
     *    2. fixedDelay/fixedDelayString
     *    3. fixedRate/fixedRateString
     *    4. initialDelay/initialDelayString
     * </pre>
     */
    @Scheduled(fixedDelay = 5 * 1000, initialDelay = 1000)
    public void runTask() {
        Task task = Task.builder().name("Spring Default Schedule").build();
        this.taskService.runTask(task);
    }
}
