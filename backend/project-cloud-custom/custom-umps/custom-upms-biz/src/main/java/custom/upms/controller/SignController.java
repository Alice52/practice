package custom.upms.controller;

import common.api.utils.PageUtils;
import custom.basic.api.entity.SignEntity;
import custom.basic.api.vo.SignVO;
import custom.upms.service.SignService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

import static custom.upms.converter.SignConverter.INSTANCE;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@Api(tags = "Sign")
@RestController
@RequestMapping("/upms")
public class SignController {

    @Resource private SignService signService;

    @GetMapping("/signs")
    public PageUtils list(@RequestParam Map<String, Object> params) {

        return signService.queryPage(params);
    }

    @GetMapping("/sign/{id}")
    public SignVO detail(@PathVariable("id") Long id) {
        SignEntity po = signService.getById(id);

        return INSTANCE.po2vo(po);
    }

    @PostMapping(value = {"/sign", "/signs"})
    public void save(@RequestBody SignVO upmsSignVO) {

        signService.save(INSTANCE.vo2po(upmsSignVO));
    }

    @PutMapping("/sign/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody SignVO upmsSignVO) {

        upmsSignVO.setId(id);
        signService.updateById(INSTANCE.vo2po(upmsSignVO));
    }

    @DeleteMapping("/signs")
    public void delete(@RequestBody Long[] ids) {

        signService.removeByIds(Arrays.asList(ids));
    }

    @DeleteMapping("/sign/{id}")
    public void deleteById(@PathVariable("id") Long id) {

        signService.removeById(id);
    }
}
