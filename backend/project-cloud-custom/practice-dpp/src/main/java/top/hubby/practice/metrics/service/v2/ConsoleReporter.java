package top.hubby.practice.metrics.service.v2;

import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.model.RequestInfo;
import top.hubby.practice.metrics.model.RequestStat;
import top.hubby.practice.metrics.service.v2.view.StatViewer;
import top.hubby.practice.metrics.store.MetricsStorage;
import top.hubby.practice.metrics.support.v2.Aggregator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-12-24 4:58 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class ConsoleReporter {
    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executor;

    public ConsoleReporter(
            MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        long durationInMillis = durationInSeconds * 1000;
                        long endTimeInMillis = System.currentTimeMillis();
                        long startTimeInMillis = endTimeInMillis - durationInMillis;
                        Map<String, List<RequestInfo>> requestInfos =
                                metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                        Map<String, RequestStat> requestStats =
                                aggregator.aggregate(requestInfos, durationInMillis);
                        viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
                    }
                },
                0L,
                periodInSeconds,
                TimeUnit.SECONDS);
    }
}
