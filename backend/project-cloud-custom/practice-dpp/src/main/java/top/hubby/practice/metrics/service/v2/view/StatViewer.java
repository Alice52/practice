package top.hubby.practice.metrics.service.v2.view;

import java.util.Map;

/**
 * @author asd <br>
 * @create 2021-12-27 10:29 AM <br>
 * @project pattern <br>
 */
public interface StatViewer {
    void output(Map requestStats, long startTimeInMillis, long endTimeInMills);
}
