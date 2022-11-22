package top.hubby.practice.metrics.store.impl;

import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.model.RequestInfo;
import top.hubby.practice.metrics.store.MetricsStorage;

import java.util.List;
import java.util.Map;

/**
 * @author asd <br>
 * @create 2021-12-24 4:57 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class RedisMetricsStorage implements MetricsStorage {
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {}

    @Override
    public List getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(
            long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}
