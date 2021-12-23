package practice.job.dynamic.service;

import practice.job.dynamic.task.Task;

/**
 * @author asd <br>
 * @create 2021-12-23 2:08 PM <br>
 * @project project-cloud-custom <br>
 */
public interface TaskService {

    void runTask(Task task);
}
