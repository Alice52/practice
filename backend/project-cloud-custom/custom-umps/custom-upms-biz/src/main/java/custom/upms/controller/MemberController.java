package custom.upms.controller;

import common.api.utils.PageUtils;
import common.core.util.R;
import custom.basic.api.entity.MemberEntity;
import custom.basic.api.vo.MemberVO;
import custom.upms.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

import static custom.upms.converter.MemberConverter.INSTANCE;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@Api(tags = "Member")
@RestController
@RequestMapping("/upms")
public class MemberController {

    @Resource private MemberService memberService;

    @GetMapping("/members")
    public R<PageUtils> list(@RequestParam Map<String, Object> params) {

        return R.success(memberService.queryPage(params));
    }

    @GetMapping("/member/{id}")
    public R<MemberVO> detail(@PathVariable("id") Long id) {
        MemberEntity po = memberService.getById(id);

        return R.success(INSTANCE.po2vo(po));
    }

    @PostMapping(value = {"/member", "/members"})
    public R<Boolean> save(@RequestBody MemberVO upmsMemberVO) {

        return R.success(memberService.save(INSTANCE.vo2po(upmsMemberVO)));
    }

    @PutMapping("/member/{id}")
    public R<Boolean> update(@PathVariable("id") Long id, @RequestBody MemberVO upmsMemberVO) {

        upmsMemberVO.setId(id);
        return R.success(memberService.updateById(INSTANCE.vo2po(upmsMemberVO)));
    }

    @DeleteMapping("/members")
    public R<Boolean> delete(@RequestBody Long[] ids) {

        return R.success(memberService.removeByIds(Arrays.asList(ids)));
    }

    @DeleteMapping("/member/{id}")
    public R<Boolean> deleteById(@PathVariable("id") Long id) {

        return R.success(memberService.removeById(id));
    }
}
