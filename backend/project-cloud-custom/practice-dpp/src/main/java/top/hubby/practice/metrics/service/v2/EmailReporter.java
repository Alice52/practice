package top.hubby.practice.metrics.service.v2;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import top.hubby.practice.metrics.service.v2.view.StatViewer;
import top.hubby.practice.metrics.store.MetricsStorage;
import top.hubby.practice.metrics.support.v2.Aggregator;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-12-27 9:43 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class EmailReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executor;

    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.viewer = viewer;
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        executor.scheduleAtFixedRate(
                () -> {
                    long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                    long endTimeInMillis = System.currentTimeMillis();
                    long startTimeInMillis = endTimeInMillis - durationInMillis;
                    val requestInfos =
                            metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                    Map stats = aggregator.aggregate(requestInfos, durationInMillis);
                    viewer.output(stats, startTimeInMillis, endTimeInMillis);
                },
                0,
                DAY_HOURS_IN_SECONDS * 1000,
                TimeUnit.SECONDS);
    }
}
