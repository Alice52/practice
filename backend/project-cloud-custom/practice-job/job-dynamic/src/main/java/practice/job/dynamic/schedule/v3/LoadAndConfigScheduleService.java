package practice.job.dynamic.schedule.v3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import javax.annotation.Resource;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import practice.job.dynamic.constants.enums.TaskStatus;
import practice.job.dynamic.model.TaskDefinition;
import practice.job.dynamic.service.TaskDefinitionService;
import practice.job.dynamic.service.TaskService;
import practice.job.dynamic.task.Task;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 当配置发送变化时, 配置中心会主动将配置推送到 应用程序, 应用程序在接收到变化通知时, 动态的增加或取消调度任务
 *
 * @author asd <br>
 * @create 2021-12-23 6:11 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Service
public class LoadAndConfigScheduleService {
    private final Map<Long, TaskEntry> taskEntry = new HashMap<>();
    @Resource private TaskService taskService;
    @Resource private TaskScheduler taskScheduler;
    @Resource private TaskDefinitionService definitionService;

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    public void loadAndConfig() {
        List<TaskDefinition> tasks = definitionService.list();
        for (TaskDefinition task : tasks) {
            TaskEntry taskEntry = this.taskEntry.computeIfAbsent(task.getId(), TaskEntry::new);
            if (TaskStatus.ENABLE.equals(task.getStatus()) && taskEntry.isStop()) {
                // 任务为可用，运行状态为停止，则重新进行 schedule 注册
                ScheduledFuture<?> scheduledFuture =
                        taskScheduler.scheduleWithFixedDelay(
                                new TaskRunner(task), task.getDelay() * 1000);
                taskEntry.setScheduledFuture(scheduledFuture);
                log.info("success to start schedule task for {}", task);

            } else if (TaskStatus.DISABLE.equals(task.getStatus()) && taskEntry.isRunning()) {
                // 任务为禁用，运行状态为运行中，停止正在运行在任务
                taskEntry.stop();
                log.info("success to stop schedule task for {}", task);
            }
        }
    }

    @Data
    private static class TaskEntry {
        private final Long taskId;
        private ScheduledFuture scheduledFuture;

        private TaskEntry(Long taskId) {
            this.taskId = taskId;
        }

        /**
         * 内存状态 scheduledFuture 为 null，则没有运行的任务
         *
         * @return
         */
        public boolean isStop() {
            return scheduledFuture == null;
        }

        /**
         * 内存状态 scheduledFuture 不为 null，则存在运行的任务
         *
         * @return
         */
        public boolean isRunning() {
            return scheduledFuture != null;
        }

        /**
         * 停止调度任务 <br>
         * 1. 内存状态设置为 null 2. 调用 scheduledFuture#cancel 终止正在运行的调度任务
         */
        public void stop() {
            ScheduledFuture scheduledFuture = this.scheduledFuture;
            this.scheduledFuture = null;

            scheduledFuture.cancel(true);
        }
    }

    private class TaskRunner implements Runnable {
        private final Long taskId;

        public TaskRunner(TaskDefinition task) {
            this.taskId = task.getId();
        }

        @Override
        public void run() {
            TaskDefinition task = definitionService.getById(this.taskId);
            if (task != null && TaskStatus.ENABLE.equals(task.getStatus())) {
                Task taskConfig = Task.builder().name(task.getName()).data(task.getData()).build();
                taskService.runTask(taskConfig);
            }
        }
    }
}
