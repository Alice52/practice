package top.hubby.practice.metrics.test.v2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.controller.v1.MetricsCollector;
import top.hubby.practice.metrics.model.RequestInfo;
import top.hubby.practice.metrics.service.v2.ConsoleReporter;
import top.hubby.practice.metrics.service.v2.EmailReporter;
import top.hubby.practice.metrics.service.v2.view.ConsoleViewer;
import top.hubby.practice.metrics.service.v2.view.EmailViewer;
import top.hubby.practice.metrics.store.MetricsStorage;
import top.hubby.practice.metrics.store.impl.RedisMetricsStorage;
import top.hubby.practice.metrics.support.v2.Aggregator;

/**
 * @author asd <br>
 * @create 2021-12-27 10:41 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class PerfCounterTest {
    @SneakyThrows
    public static void main(String[] args) {
        MetricsStorage storage = new RedisMetricsStorage();
        Aggregator aggregator = new Aggregator();

        // 定时触发统计并将结果显示到终端
        ConsoleViewer consoleViewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = new ConsoleReporter(storage, aggregator, consoleViewer);
        consoleReporter.startRepeatedReport(60, 60);

        // 定时触发统计并将结果输出到邮件
        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("wangzheng@xzg.com");
        EmailReporter emailReporter = new EmailReporter(storage, aggregator, emailViewer);
        emailReporter.startDailyReport();

        // 收集接口访问数据
        MetricsCollector collector = new MetricsCollector(storage);
        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));

        Thread.sleep(100000);
    }
}
