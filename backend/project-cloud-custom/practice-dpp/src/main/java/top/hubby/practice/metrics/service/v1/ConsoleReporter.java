package top.hubby.practice.metrics.service.v1;

import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.model.RequestInfo;
import top.hubby.practice.metrics.model.RequestStat;
import top.hubby.practice.metrics.store.MetricsStorage;
import top.hubby.practice.metrics.support.v1.Aggregator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * <pre>
 *     1. 职责不单一
 *     2. 有重复代码
 *     3. 测试性不好
 * </pre>
 *
 * @author asd <br>
 * @create 2021-12-24 4:58 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class ConsoleReporter {
    private MetricsStorage metricsStorage;
    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    // 第4个代码逻辑：定时触发第1、2、3代码逻辑的执行；
    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(
                () -> {
                    // 第1个代码逻辑：根据给定的时间区间，从数据库中拉取数据；
                    long durationInMillis = durationInSeconds * 1000;
                    long endTimeInMillis = System.currentTimeMillis();
                    long startTimeInMillis = endTimeInMillis - durationInMillis;
                    Map<String, List<RequestInfo>> requestInfos =
                            metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                    Map<String, RequestStat> stats = new HashMap<>();
                    for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
                        String apiName = entry.getKey();
                        List<RequestInfo> requestInfosPerApi = entry.getValue();
                        // 第2个代码逻辑：根据原始数据，计算得到统计数据；
                        RequestStat requestStat =
                                Aggregator.aggregate(requestInfosPerApi, durationInMillis);
                        stats.put(apiName, requestStat);
                    }
                    // 第3个代码逻辑：将统计数据显示到终端（命令行或邮件）；
                    log.info("Time Span: [" + startTimeInMillis + ", " + endTimeInMillis + "]");
                },
                0,
                periodInSeconds,
                TimeUnit.SECONDS);
    }
}
