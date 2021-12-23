package top.hubby.job.delay.domain.order.entity.valueobject;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-11-26 1:33 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
public enum OrderInfoStatus {
    CREATED,
    PAID_SUCCESS,
    CANCELLED
}
