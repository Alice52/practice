package top.hubby.practice.metrics.service.v1;

import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.model.RequestInfo;
import top.hubby.practice.metrics.model.RequestStat;
import top.hubby.practice.metrics.store.MetricsStorage;
import top.hubby.practice.metrics.support.EmailSender;
import top.hubby.practice.metrics.support.v1.Aggregator;

import java.util.*;
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
    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();
    private ScheduledExecutorService executor;

    public EmailReporter(MetricsStorage metricsStorage) {
        this(metricsStorage, new EmailSender());
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public EmailReporter(MetricsStorage metricsStorage, EmailSender emailSender) {
        this.metricsStorage = metricsStorage;
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstTime = calendar.getTime();
        executor.scheduleAtFixedRate(
                () -> {
                    long durationInMillis = DAY_HOURS_IN_SECONDS * 1000;
                    long endTimeInMillis = System.currentTimeMillis();
                    long startTimeInMillis = endTimeInMillis - durationInMillis;
                    Map<String, List<RequestInfo>> requestInfos =
                            metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
                    Map<String, RequestStat> stats = new HashMap<>();
                    for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
                        String apiName = entry.getKey();
                        List<RequestInfo> requestInfosPerApi = entry.getValue();
                        RequestStat requestStat =
                                Aggregator.aggregate(requestInfosPerApi, durationInMillis);
                        stats.put(apiName, requestStat);
                    }
                    // TODO: 格式化为html格式，并且发送邮件
                },
                0,
                DAY_HOURS_IN_SECONDS * 1000,
                TimeUnit.SECONDS);
    }
}
