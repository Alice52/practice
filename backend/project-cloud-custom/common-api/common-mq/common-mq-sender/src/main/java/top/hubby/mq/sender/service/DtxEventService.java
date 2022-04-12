package top.hubby.mq.sender.service;
import com.baomidou.mybatisplus.extension.service.IService;

import top.hubby.mq.constants.enums.EventStatus;
import top.hubby.mq.model.entity.DtxEvent;

/**
 *
 * @author zack <br>
 * @create 2021-06-23<br>
 * @project project-custom <br>
 */
public interface DtxEventService extends IService<DtxEvent> {


     boolean updateEventStatus(Long id, EventStatus status);

}
