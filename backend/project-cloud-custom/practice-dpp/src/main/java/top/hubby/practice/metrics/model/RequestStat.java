package top.hubby.practice.metrics.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-12-27 9:39 AM <br>
 * @project pattern <br>
 */
@Slf4j
@Data
public class RequestStat {
    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;
}
