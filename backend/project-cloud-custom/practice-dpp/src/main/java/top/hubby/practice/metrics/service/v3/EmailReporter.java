package top.hubby.practice.metrics.service.v3;

import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.service.v2.view.EmailViewer;
import top.hubby.practice.metrics.service.v2.view.StatViewer;
import top.hubby.practice.metrics.store.MetricsStorage;
import top.hubby.practice.metrics.store.impl.RedisMetricsStorage;
import top.hubby.practice.metrics.support.v2.Aggregator;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-12-27 1:35 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class EmailReporter extends ScheduledReporter {
    private static final Long DAY_HOURS_IN_SECONDS = 86400L;

    private MetricsStorage metricsStorage;
    private Aggregator aggregator;
    private StatViewer viewer;
    private ScheduledExecutorService executor;

    public EmailReporter() {
        this(new RedisMetricsStorage(), new Aggregator(), new EmailViewer());
    }

    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer viewer) {
        super(metricsStorage, aggregator, viewer);
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
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
                    doStatAndReport(startTimeInMillis, endTimeInMillis);
                },
                0,
                DAY_HOURS_IN_SECONDS * 1000,
                TimeUnit.SECONDS);
    }
}
