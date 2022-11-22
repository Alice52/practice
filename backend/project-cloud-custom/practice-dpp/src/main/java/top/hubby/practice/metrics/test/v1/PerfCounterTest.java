package top.hubby.practice.metrics.test.v1;

import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.controller.v1.MetricsCollector;
import top.hubby.practice.metrics.model.RequestInfo;
import top.hubby.practice.metrics.service.v1.ConsoleReporter;
import top.hubby.practice.metrics.service.v1.EmailReporter;
import top.hubby.practice.metrics.store.MetricsStorage;
import top.hubby.practice.metrics.store.impl.RedisMetricsStorage;

/**
 * @author asd <br>
 * @create 2021-12-27 10:42 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class PerfCounterTest {
    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage);
        consoleReporter.startRepeatedReport(60, 60);

        EmailReporter emailReporter = new EmailReporter(storage);
        emailReporter.addToAddress("wangzheng@xzg.com");
        emailReporter.startDailyReport();

        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123.0, 10234L));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
