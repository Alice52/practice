package custom.upms.controller;

import common.api.utils.PageUtils;
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
    public PageUtils list(@RequestParam Map<String, Object> params) {

        return memberService.queryPage(params);
    }

    @GetMapping("/member/{id}")
    public MemberVO detail(@PathVariable("id") Long id) {
        MemberEntity po = memberService.getById(id);

        return INSTANCE.po2vo(po);
    }

    @PostMapping(value = {"/member", "/members"})
    public void save(@RequestBody MemberVO upmsMemberVO) {

        memberService.save(INSTANCE.vo2po(upmsMemberVO));
    }

    @PutMapping("/member/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody MemberVO upmsMemberVO) {

        upmsMemberVO.setId(id);
        memberService.updateById(INSTANCE.vo2po(upmsMemberVO));
    }

    @DeleteMapping("/members")
    public void delete(@RequestBody Long[] ids) {

        memberService.removeByIds(Arrays.asList(ids));
    }

    @DeleteMapping("/member/{id}")
    public void deleteById(@PathVariable("id") Long id) {

        memberService.removeById(id);
    }
}
