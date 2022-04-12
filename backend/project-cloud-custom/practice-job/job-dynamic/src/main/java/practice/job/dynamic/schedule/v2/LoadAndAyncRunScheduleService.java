package practice.job.dynamic.schedule.v2;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import practice.job.dynamic.constants.enums.TaskStatus;
import practice.job.dynamic.model.TaskDefinition;
import practice.job.dynamic.service.TaskDefinitionService;
import practice.job.dynamic.service.TaskService;
import practice.job.dynamic.task.Task;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-12-23 3:13 PM <br>
 * @project project-cloud-custom <br>
 */
@Service
public class LoadAndAyncRunScheduleService {
    @Resource private TaskService taskService;
    @Resource private TaskDefinitionService definitionService;

    /**
     * 线程池注册器, 每个任务一个线程
     *
     * <pre>
     *    1. 每个线程池最多只有一个线程
     *    2. 线程空闲超过 10秒 进行自动回收
     *    3. 直接使用交互器，线程空闲进行任务交互
     *    4. 使用指定的线程工厂，设置线性名称
     *    5. 线程池饱和，自动丢弃最老的任务
     * </pre>
     */
    private Map<Long, ExecutorService> executorServiceRegistry = new HashMap<>();

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void loadAndRunTask() {
        LocalDateTime now = LocalDateTime.now();
        // 加载所有待运行的任务
        List<TaskDefinition> shouldRunTasks = loadShouldRunTasks(now);
        for (TaskDefinition task : shouldRunTasks) {
            this.executorServiceForTask(task.getId()).submit(new TaskRunner(task.getId()));
        }
    }

    private List<TaskDefinition> loadShouldRunTasks(LocalDateTime now) {
        return definitionService.getByStatusAndNextRunAtLessThan(TaskStatus.ENABLE, now);
    }

    private ExecutorService executorServiceForTask(Long taskId) {
        return this.executorServiceRegistry.computeIfAbsent(
                taskId,
                id -> {
                    BasicThreadFactory threadFactory =
                            new BasicThreadFactory.Builder()
                                    .namingPattern("Async-Task-" + taskId + "-Thread-%d")
                                    // 设置线程为 后台线程
                                    .daemon(true)
                                    .build();

                    return new ThreadPoolExecutor(
                            0,
                            1,
                            10L,
                            TimeUnit.SECONDS,
                            new SynchronousQueue<>(),
                            threadFactory,
                            new ThreadPoolExecutor.DiscardOldestPolicy());
                });
    }

    private class TaskRunner implements Runnable {
        private final LocalDateTime now = LocalDateTime.now();
        private final Long taskId;

        public TaskRunner(Long taskId) {
            this.taskId = taskId;
        }

        @Override
        public void run() {
            // 重新加载任务，保持最新的任务状态
            TaskDefinition task = definitionService.getById(this.taskId);
            if (task != null && task.shouldRun(now)) {
                // 运行任务
                runTask(task);

                // 更新任务的下一次运行时间
                updateNextRunTime(task, now);
            }
        }

        private void updateNextRunTime(TaskDefinition task, LocalDateTime now) {
            task.updateNextRunTime(now);
            definitionService.save(task);
        }

        private void runTask(TaskDefinition task) {
            Task taskConfig = Task.builder().name(task.getName()).data(task.getData()).build();
            taskService.runTask(taskConfig);
        }
    }
}
