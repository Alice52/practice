package practice.job.dynamic.schedule.v2;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import practice.job.dynamic.constants.enums.TaskStatus;
import practice.job.dynamic.model.TaskDefinition;
import practice.job.dynamic.service.TaskDefinitionService;
import practice.job.dynamic.service.TaskService;
import practice.job.dynamic.task.Task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 同步的会有延迟等问题
 *
 * @author asd <br>
 * @create 2021-12-23 2:59 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Service
@Deprecated
public class LoadAndRunScheduleService {
    @Resource private TaskService taskService;
    @Resource private TaskDefinitionService definitionService;
    @Resource private RedissonClient redissonClient;

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void loadAndRunTask() {
        LocalDateTime now = LocalDateTime.now();
        // 加载需要运行的任务：
        List<TaskDefinition> shouldRunTasks = loadShouldRunTasks(now);

        // 依次遍历待运行任务，执行对于的任务
        for (TaskDefinition task : shouldRunTasks) {
            // Double Check
            if (task.shouldRun(now)) {
                runTask(task);
                updateNextRunTime(task, now);
            }
        }
    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void loadAndRunTaskWithLock() {
        LocalDateTime now = LocalDateTime.now();
        // 加载需要运行的任务：
        List<TaskDefinition> shouldRunTasks = loadShouldRunTasks(now);
        // 依次遍历待运行任务，执行对于的任务
        for (TaskDefinition task : shouldRunTasks) {
            // Double Check
            if (task.shouldRun(now)) {
                // 获取分布式锁，用于保证每个任务只能有一个正在运行
                RLock lock =
                        this.redissonClient.getFairLock(
                                "LoadAndRunScheduleService-" + task.getId());
                if (lock.tryLock()) {
                    try {
                        log.info("Success to get Lock, begin to run task {}", task.getId());
                        runTask(task);
                        updateNextRunTime(task, now);
                        log.info("Success to run task {}", task.getId());
                    } finally {
                        lock.unlock();
                    }
                } else {
                    log.info("Failed to get Lock!!!!");
                }
            }
        }
    }

    private List<TaskDefinition> loadShouldRunTasks(LocalDateTime now) {
        return definitionService.getByStatusAndNextRunAtLessThan(TaskStatus.ENABLE, now);
    }

    private void updateNextRunTime(TaskDefinition task, LocalDateTime now) {
        task.updateNextRunTime(now);
        definitionService.saveOrUpdate(task);
    }

    private void runTask(TaskDefinition task) {
        Task taskConfig = Task.builder().name(task.getName()).data(task.getData()).build();
        this.taskService.runTask(taskConfig);
    }
}
