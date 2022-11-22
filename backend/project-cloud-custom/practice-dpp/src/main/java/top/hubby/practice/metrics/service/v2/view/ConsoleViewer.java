package top.hubby.practice.metrics.service.v2.view;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author asd <br>
 * @create 2021-12-27 10:29 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class ConsoleViewer implements StatViewer {
    @Override
    public void output(Map requestStats, long startTimeInMillis, long endTimeInMills) {
        log.info("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        log.info("{}", requestStats);
    }
}
