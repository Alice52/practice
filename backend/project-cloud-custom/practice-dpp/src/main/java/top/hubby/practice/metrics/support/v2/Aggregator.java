package top.hubby.practice.metrics.support.v2;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.model.RequestInfo;
import top.hubby.practice.metrics.model.RequestStat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author asd <br>
 * @create 2021-12-24 4:57 PM <br>
 * @project pattern <br>
 */
@Slf4j
@Data
public class Aggregator {
    public Map<String, RequestStat> aggregate(
            Map<String, List<RequestInfo>> requestInfos, long durationInMillis) {
        Map<String, RequestStat> requestStats = new HashMap<>();
        for (Map.Entry<String, List<RequestInfo>> entry : requestInfos.entrySet()) {
            RequestStat requestStat = doAggregate(entry.getValue(), durationInMillis);
            requestStats.put(entry.getKey(), requestStat);
        }
        return requestStats;
    }

    private RequestStat doAggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        List<Double> respTimes = new ArrayList<>();
        for (RequestInfo requestInfo : requestInfos) {
            double respTime = requestInfo.getResponseTime();
            respTimes.add(respTime);
        }

        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(max(respTimes));
        requestStat.setMinResponseTime(min(respTimes));
        requestStat.setAvgResponseTime(avg(respTimes));
        requestStat.setP999ResponseTime(percentile999(respTimes));
        requestStat.setP99ResponseTime(percentile99(respTimes));
        requestStat.setCount(respTimes.size());
        requestStat.setTps((long) tps(respTimes.size(), durationInMillis / 1000));
        return requestStat;
    }

    // 以下的函数的代码实现均省略...
    private double max(List<Double> dataset) {
        throw new UnsupportedOperationException();
    }

    private double min(List<Double> dataset) {
        throw new UnsupportedOperationException();
    }

    private double avg(List<Double> dataset) {
        throw new UnsupportedOperationException();
    }

    private double tps(int count, double duration) {
        throw new UnsupportedOperationException();
    }

    private double percentile999(List<Double> dataset) {
        throw new UnsupportedOperationException();
    }

    private double percentile99(List<Double> dataset) {
        throw new UnsupportedOperationException();
    }

    private double percentile(List<Double> dataset, double ratio) {
        throw new UnsupportedOperationException();
    }
}
