package top.hubby.practice.metrics.controller.v1;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.model.RequestInfo;
import top.hubby.practice.metrics.store.MetricsStorage;
import top.hubby.practice.metrics.store.impl.RedisMetricsStorage;

/**
 * @author asd <br>
 * @create 2021-12-24 4:56 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class MetricsCollector {
    private MetricsStorage metricsStorage;

    public MetricsCollector() {
        this(new RedisMetricsStorage());
    }

    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StrUtil.isBlank(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
