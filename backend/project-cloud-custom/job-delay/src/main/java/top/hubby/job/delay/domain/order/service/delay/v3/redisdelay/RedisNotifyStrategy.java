package top.hubby.job.delay.domain.order.service.delay.v3.redisdelay;

import lombok.extern.slf4j.Slf4j;

/**
 * Redis的发布/订阅目前是即发即弃(fire and forget)模式的 <br>
 *
 * <pre>
 *   - 因此无法实现事件的可靠通知
 *   - 如果发布/订阅的客户端断链之后又重连, 则在客户端断链期间的所有事件都丢失了
 * </pre>
 *
 * @author asd <br>
 * @create 2021-12-03 4:29 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Deprecated
public class RedisNotifyStrategy {

    // 相当于 redis 的MQ

}
