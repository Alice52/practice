package top.hubby.mq.sender.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.hubby.mq.model.entity.DtxEvent;

/**
 * @author zack <br/>
 * @create 2022-04-12 12:07 <br/>
 * @project project-cloud-custom <br/>
 */
@Mapper
public interface DtxEventMapper extends BaseMapper<DtxEvent> {

}
