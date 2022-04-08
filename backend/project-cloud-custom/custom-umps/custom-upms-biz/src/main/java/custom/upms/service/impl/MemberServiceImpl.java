package custom.upms.service.impl;

import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import common.api.utils.CommonQuery;
import common.api.utils.PageUtils;
import custom.basic.api.entity.MemberEntity;
import custom.upms.mapper.MemberMapper;
import custom.upms.service.MemberService;

import org.springframework.stereotype.Service;

/**
 * @author zack.zhang <br>
 * @create 2021-08-15 09:03:18 <br>
 * @project ware <br>
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, MemberEntity>
        implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page =
                this.page(
                        new CommonQuery<MemberEntity>().getPage(params),
                        new QueryWrapper<MemberEntity>());

        return new PageUtils(page);
    }
}
