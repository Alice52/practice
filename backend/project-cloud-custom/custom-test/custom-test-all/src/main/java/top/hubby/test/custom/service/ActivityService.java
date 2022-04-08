package top.hubby.test.custom.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.extension.service.IService;
import top.hubby.test.custom.model.entity.Activity;
import top.hubby.test.custom.model.vo.ActivityVO;

import org.springframework.validation.annotation.Validated;

/**
 * @author zack <br>
 * @create 2021-04-09 10:24 <br>
 * @project integration <br>
 */
@Validated
public interface ActivityService extends IService<Activity> {

    /**
     * 获取某些阶段对应的活动
     *
     * @param phaseIds
     * @return
     */
    List<ActivityVO> queryByPhaseIds(@NotNull(message = "阶段Id不能为空") List<Long> phaseIds);
}
