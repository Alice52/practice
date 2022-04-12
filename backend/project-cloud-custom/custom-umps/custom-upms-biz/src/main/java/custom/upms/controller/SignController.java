package custom.upms.controller;

import cn.hutool.core.util.StrUtil;
import common.api.utils.PageUtils;
import common.core.constant.enums.BusinessResponseEnum;
import common.core.util.R;
import custom.basic.api.entity.SignEntity;
import custom.basic.api.vo.SignVO;
import custom.upms.service.SignService;
import custom.upms.utils.DateTimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;

import static cn.hutool.core.date.DatePattern.NORM_DATE_PATTERN;
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
    public R<PageUtils> list(@RequestParam Map<String, Object> params) {

        return R.success(signService.queryPage(params));
    }

    @GetMapping("/sign/{id}")
    public R<SignVO> detail(@PathVariable("id") Long id) {
        SignEntity po = signService.getById(id);

        return R.success(INSTANCE.po2vo(po));
    }
    //
    //    @PostMapping(value = {"/sign", "/signs"})
    //    public R<Boolean> save(@RequestBody SignVO upmsSignVO) {
    //
    //        return R.success(signService.save(INSTANCE.vo2po(upmsSignVO)));
    //    }

    @PutMapping("/sign/{id}")
    public R<Boolean> update(@PathVariable("id") Long id, @RequestBody SignVO upmsSignVO) {

        upmsSignVO.setId(id);
        return R.success(signService.updateById(INSTANCE.vo2po(upmsSignVO)));
    }

    @DeleteMapping("/signs")
    public R<Boolean> delete(@RequestBody Long[] ids) {

        return R.success(signService.removeByIds(Arrays.asList(ids)));
    }

    @DeleteMapping("/sign/{id}")
    public R<Boolean> deleteById(@PathVariable("id") Long id) {

        return R.success(signService.removeById(id));
    }

    @ApiOperation("获取用户签到情况 默认当月")
    @GetMapping("/sign/month/{dateStr}")
    public R<Map<String, Boolean>> getSignInfo(
            Long memberId, @PathVariable("dateStr") String dateStr) {

        BusinessResponseEnum.PARAMTER_ERROR.assertIsTrue(DateTimeUtil.isDate(dateStr));
        LocalDate monthDate =
                LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(NORM_DATE_PATTERN));
        return R.success(signService.getSignInfo(memberId, monthDate));
    }

    @ApiOperation("获取签到次数 默认当月")
    @GetMapping("/sign/count/month/{dateStr}")
    public R<Long> getSignCount(Long memberId, @PathVariable("dateStr") String dateStr) {
        BusinessResponseEnum.PARAMTER_ERROR.assertIsTrue(DateTimeUtil.isDate(dateStr));
        LocalDate monthDate =
                LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(NORM_DATE_PATTERN));
        return R.success(signService.getSignCount(memberId, monthDate));
    }

    @ApiOperation("签到，可以补签")
    @PostMapping("/sign")
    public R<Integer> sign(@NotNull Long memberId, @RequestParam(required = false) String dateStr) {

        LocalDate date;
        if (StrUtil.isBlank(dateStr)) {
            date = LocalDate.now();
        } else {
            BusinessResponseEnum.PARAMTER_ERROR.assertIsTrue(DateTimeUtil.isDate(dateStr));
            date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(NORM_DATE_PATTERN));
        }

        return R.success(signService.doSign(memberId, date));
    }
}
