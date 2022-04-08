package top.hubby.test.custom.controler;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.groups.Default;

import common.core.util.R;
import common.core.util.ValidatorGroupUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import top.hubby.test.custom.model.dto.PhaseDTO;
import top.hubby.test.custom.model.vo.PhaseVO;
import top.hubby.test.custom.service.impl.PhaseServiceV2Impl;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zack <br>
 * @create 2021-04-09 10:10 <br>
 * @project integration <br>
 */
@Slf4j
@Api(tags = {"Phase Manage Api"})
@RestController
@RequestMapping("/custom/v2")
public class PhaseV2Controller {
    @Resource private PhaseServiceV2Impl phaseService;

    @GetMapping("/phases")
    public R<List<PhaseVO>> list(
            @RequestParam(value = "type", required = false) @ApiParam(value = "活动标识") String type) {

        return R.success(phaseService.listPhases(type));
    }

    @ApiOperation("data de-sensitive")
    @GetMapping("/phase/{id}")
    public R<PhaseVO> get(
            @PathVariable("id") Long id,
            @RequestParam(value = "type", required = false) @ApiParam(value = "活动标识") String type) {

        return R.success(phaseService.getPhase(id, type));
    }

    @PutMapping("/phase/{id}")
    public R<Boolean> update(@PathVariable("id") Long id, @RequestBody PhaseDTO phase) {
        phase.setId(id);
        return R.success(phaseService.updatePhase(phase));
    }

    @DeleteMapping("/phase/{id}")
    public R<Boolean> delete(@PathVariable("id") Long id) {
        return R.success(phaseService.deletePhase(id));
    }

    @PostMapping("/phase")
    public R<Boolean> create(
            @RequestBody @Validated({ValidatorGroupUtil.Add.class, Default.class}) PhaseDTO phase) {
        return R.success(phaseService.createPhase(phase));
    }
}
