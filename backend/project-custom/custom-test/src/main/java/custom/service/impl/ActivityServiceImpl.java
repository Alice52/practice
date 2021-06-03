package custom.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import custom.mapper.ActivityMapper;
import custom.model.entity.Activity;
import custom.model.vo.ActivityVO;
import custom.service.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static custom.converter.ActivityConverter.CONVERTER;

/**
 * @author zack <br>
 * @create 2021-04-09 10:24 <br>
 * @project integration <br>
 */
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
        implements ActivityService {

    @Override
    public List<ActivityVO> queryByPhaseIds(List<Long> phaseIds) {

        return this.list(Wrappers.<Activity>query().lambda().in(Activity::getPhaseId, phaseIds))
                .stream()
                .map(CONVERTER::po2vo)
                .collect(Collectors.toList());
    }
}
