package top.hubby.job.delay.domain.order.repository.po;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.hubby.job.delay.domain.order.entity.valueobject.OrderInfoStatus;

import org.springframework.data.annotation.Id;

/**
 * @author asd <br>
 * @create 2021-11-26 1:31 PM <br>
 * @project project-cloud-custom <br>
 */
@Data
@TableName("practice_order_info")
public class OrderInfo {

    @Id private Long id;

    @TableField("status")
    private OrderInfoStatus orderStatus;

    @TableField("create_time")
    private LocalDateTime createTime = LocalDateTime.now();

    /**
     * 创建订单
     *
     * @param createDate
     * @return
     */
    public static OrderInfo create(LocalDateTime createDate) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateTime(createDate);
        orderInfo.setOrderStatus(OrderInfoStatus.CREATED);
        return orderInfo;
    }

    /** 取消订单 */
    public void cancel() {
        setOrderStatus(OrderInfoStatus.CANCELLED);
    }
}
