package top.hubby.practice.metrics.support.v1;

import lombok.extern.slf4j.Slf4j;
import top.hubby.practice.metrics.model.RequestInfo;
import top.hubby.practice.metrics.model.RequestStat;

import java.util.List;

/**
 *
 *
 * <pre>
 *     1. 静态函数: 可测试性不好
 *     2. 逻辑复杂且耦合
 *     3. 可读性, 维护性, 扩张性差
 * </pre>
 *
 * @author asd <br>
 * @create 2021-12-24 4:57 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class Aggregator {
    public static RequestStat aggregate(List<RequestInfo> requestInfos, long durationInMillis) {
        double maxRespTime = Double.MIN_VALUE;
        double minRespTime = Double.MAX_VALUE;
        double avgRespTime = -1;
        double p999RespTime = -1;
        double p99RespTime = -1;
        double sumRespTime = 0;
        long count = 0;
        for (RequestInfo requestInfo : requestInfos) {
            ++count;
            double respTime = requestInfo.getResponseTime();
            if (maxRespTime < respTime) {
                maxRespTime = respTime;
            }
            if (minRespTime > respTime) {
                minRespTime = respTime;
            }
            sumRespTime += respTime;
        }
        if (count != 0) {
            avgRespTime = sumRespTime / count;
        }
        long tps = (count / durationInMillis * 1000);
        requestInfos.sort(
                (o1, o2) -> Double.compare(o1.getResponseTime() - o2.getResponseTime(), 0.0));
        int idx999 = (int) (count * 0.999);
        int idx99 = (int) (count * 0.99);
        if (count != 0) {
            p999RespTime = requestInfos.get(idx999).getResponseTime();
            p99RespTime = requestInfos.get(idx99).getResponseTime();
        }
        RequestStat requestStat = new RequestStat();
        requestStat.setMaxResponseTime(maxRespTime);
        requestStat.setMinResponseTime(minRespTime);
        requestStat.setAvgResponseTime(avgRespTime);
        requestStat.setP999ResponseTime(p999RespTime);
        requestStat.setP99ResponseTime(p99RespTime);
        requestStat.setCount(count);
        requestStat.setTps(tps);
        return requestStat;
    }
}
