package top.hubby.test.custom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.hubby.test.custom.mapper.ActivityMapper;
import top.hubby.test.custom.model.entity.Activity;
import top.hubby.test.custom.model.vo.ActivityVO;
import top.hubby.test.custom.service.ActivityService;

import org.springframework.stereotype.Service;

import static top.hubby.test.custom.converter.ActivityConverter.CONVERTER;

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
