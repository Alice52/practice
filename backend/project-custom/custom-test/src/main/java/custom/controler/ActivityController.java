package custom.controler;

import custom.model.vo.ActivityVO;
import custom.service.ActivityService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zack <br>
 * @create 2021-04-09 10:10 <br>
 * @project integration <br>
 */
@Slf4j
@Api(tags = {"活动信息"})
@RestController
@RequestMapping("/custom")
public class ActivityController {
    @Resource private ActivityService activityService;

    @GetMapping("/activities")
    public List<ActivityVO> list(@RequestParam(value = "id") List<Long> ids) {

        return activityService.queryByPhaseIds(ids);
    }
}
