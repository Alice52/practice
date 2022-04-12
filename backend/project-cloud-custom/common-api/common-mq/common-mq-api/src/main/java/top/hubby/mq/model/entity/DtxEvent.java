package top.hubby.mq.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import common.database.model.entity.SimpleEntity;
import lombok.Data;
import top.hubby.mq.constants.enums.EventStatus;

import static com.baomidou.mybatisplus.annotation.IdType.AUTO;

/**
 * @author zack <br/>
 * @create 2022-04-12 11:59 <br/>
 * @project project-cloud-custom <br/>
 */
@Data
@TableName("tx_event")
public class DtxEvent  extends SimpleEntity {
        private static final long serialVersionUID = 1L;

        @TableId(type = AUTO)
        private Long id;

        private String eventType;

        private EventStatus status;

        private String content;
}
