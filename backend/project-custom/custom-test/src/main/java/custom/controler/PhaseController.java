package custom.controler;

import common.core.util.ValidatorUtil;
import custom.model.dto.PhaseDTO;
import custom.model.vo.PhaseVO;
import custom.service.PhaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.util.List;

/**
 * @author zack <br>
 * @create 2021-04-09 10:10 <br>
 * @project integration <br>
 */
@Slf4j
@Api(tags = {"活动阶段信息"})
@RestController
@RequestMapping("/custom")
public class PhaseController {
    @Resource private PhaseService phaseService;

    @GetMapping("/phases")
    public List<PhaseVO> list(
            @RequestParam(value = "type", required = false) @ApiParam(value = "活动标识") String type) {

        return phaseService.listPhases(type);
    }

    @GetMapping("/phase/{id}")
    public PhaseVO get(
            @PathVariable("id") Long id,
            @RequestParam(value = "type", required = false) @ApiParam(value = "活动标识") String type) {

        return phaseService.getPhase(id, type);
    }

    @PutMapping("/phase/{id}")
    public Boolean update(@PathVariable("id") Long id, @RequestBody PhaseDTO phase) {
        phase.setId(id);
        return phaseService.updatePhase(phase);
    }

    @DeleteMapping("/phase/{id}")
    public Boolean delete(@PathVariable("id") Long id) {
        return phaseService.deletePhase(id);
    }

    @PostMapping("/phase")
    public Boolean create(
            @RequestBody @Validated({ValidatorUtil.Add.class, Default.class}) PhaseDTO phase) {
        return phaseService.createPhase(phase);
    }
}
