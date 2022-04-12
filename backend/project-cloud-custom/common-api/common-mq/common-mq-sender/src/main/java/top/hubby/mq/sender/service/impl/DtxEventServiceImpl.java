package top.hubby.mq.sender.service.impl;

import top.hubby.mq.constants.enums.EventStatus;
import top.hubby.mq.model.entity.DtxEvent;
import top.hubby.mq.sender.service.DtxEventService;

/**
 * @author zack <br/>
 * @create 2022-04-12 12:10 <br/>
 * @project project-cloud-custom <br/>
 */
public class DtxEventServiceImpl extends ServiceImpl<DtxEventMapper, DtxEvent>
        implements DtxEventService {

    @Override
    public boolean updateEventStatus(Long id, EventStatus status) {

        return lambdaUpdate()
                .set(DtxEvent::getStatus, status)
                .eq(DtxEvent::getId, id)
                .update();
    }
}
