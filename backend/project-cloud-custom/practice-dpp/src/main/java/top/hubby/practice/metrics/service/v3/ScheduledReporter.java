package top.hubby.practice.metrics.service.v3;

import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.model.RequestInfo;
import top.hubby.practice.metrics.model.RequestStat;
import top.hubby.practice.metrics.service.v2.view.StatViewer;
import top.hubby.practice.metrics.store.MetricsStorage;
import top.hubby.practice.metrics.support.v2.Aggregator;

import java.util.List;
import java.util.Map;

/**
 * @author asd <br>
 * @create 2021-12-27 1:35 PM <br>
 * @project pattern <br>
 */
@Slf4j
public abstract class ScheduledReporter {
    protected MetricsStorage metricsStorage;
    protected Aggregator aggregator;
    protected StatViewer viewer;

    public ScheduledReporter(
            MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
    }

    protected void doStatAndReport(long startTimeInMillis, long endTimeInMillis) {
        long durationInMillis = endTimeInMillis - startTimeInMillis;
        Map<String, List<RequestInfo>> requestInfos =
                metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        Map<String, RequestStat> requestStats =
                aggregator.aggregate(requestInfos, durationInMillis);
        viewer.output(requestStats, startTimeInMillis, endTimeInMillis);
    }

    protected abstract void startDailyReport();
}
