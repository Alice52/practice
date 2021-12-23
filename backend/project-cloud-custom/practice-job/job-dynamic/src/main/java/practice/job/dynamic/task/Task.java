package practice.job.dynamic.task;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-12-23 2:11 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Data
@Builder
public class Task {

    private String name;
    private String data;
}
