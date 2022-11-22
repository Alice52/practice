package top.hubby.practice.metrics.store;

import top.hubby.practice.metrics.model.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * @author asd <br>
 * @create 2021-12-24 4:57 PM <br>
 * @project pattern <br>
 */
public interface MetricsStorage {
    void saveRequestInfo(RequestInfo requestInfo);

    List getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
