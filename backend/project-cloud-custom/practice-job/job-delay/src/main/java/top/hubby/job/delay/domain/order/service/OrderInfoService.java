package top.hubby.job.delay.domain.order.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.hubby.job.delay.domain.order.entity.valueobject.OrderInfoStatus;
import top.hubby.job.delay.domain.order.event.OrderInfoCreateEvent;
import top.hubby.job.delay.domain.order.repository.mapper.OrderInfoMapper;
import top.hubby.job.delay.domain.order.repository.po.OrderInfo;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author asd <br>
 * @create 2021-11-26 1:33 PM <br>
 * @project project-cloud-custom <br>
 */
@Service
@Slf4j
public class OrderInfoService {
    @Resource private ApplicationEventPublisher eventPublisher;
    @Resource private OrderInfoMapper orderInfoMapper;

    /**
     * 生单接口 <br>
     * 1. 创建订单，保存至数据库 <br>
     * 2. 发布领域事件，触发后续处理
     *
     * @param dateTime
     */
    @Transactional(readOnly = false)
    public void create(LocalDateTime dateTime) {
        OrderInfo orderInfo = OrderInfo.create(dateTime);
        orderInfoMapper.insert(orderInfo);
        eventPublisher.publishEvent(new OrderInfoCreateEvent(orderInfo));
    }

    /**
     * 取消订单
     *
     * @param orderId
     */
    @Transactional(readOnly = false)
    public void cancel(Long orderId) {
        val wrapper =
                Wrappers.<OrderInfo>lambdaQuery()
                        .eq(OrderInfo::getOrderStatus, OrderInfoStatus.CREATED)
                        .eq(OrderInfo::getId, orderId);

        OrderInfo orderInfo = orderInfoMapper.selectOne(wrapper);
        if (ObjectUtil.isNotNull(orderInfo)) {
            orderInfo.cancel();
            orderInfoMapper.updateById(orderInfo);
            log.info("success to cancel order {}", orderId);
        } else {
            log.info("failed to find order {}", orderId);
        }
    }

    /**
     * 查找超时未支付的订单
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<OrderInfo> findOvertimeNotPaidOrders(LocalDateTime deadLine) {

        val wrapper =
                Wrappers.<OrderInfo>lambdaQuery()
                        .eq(OrderInfo::getOrderStatus, OrderInfoStatus.CREATED)
                        .lt(OrderInfo::getCreateTime, deadLine);

        return this.orderInfoMapper.selectList(wrapper);
    }
}
