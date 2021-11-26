package top.hubby.job.delay.domain.order.service.delay.v5.dcpmq.support.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author asd <br>
 * @create 2021-11-26 4:56 PM <br>
 * @project project-cloud-custom <br>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RocketMQBasedDelay {
    /**
     * RocketMQ topic
     *
     * @return
     */
    String topic();

    /**
     * 延时级别
     *
     * @return
     */
    int delayLevel();

    /**
     * 消费者组信息
     *
     * @return
     */
    String consumerGroup();
}
