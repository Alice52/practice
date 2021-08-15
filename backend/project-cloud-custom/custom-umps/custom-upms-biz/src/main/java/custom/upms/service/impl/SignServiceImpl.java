package custom.upms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import common.api.utils.CommonQuery;
import common.api.utils.PageUtils;
import custom.basic.api.entity.SignEntity;
import custom.upms.mapper.SignMapper;
import custom.upms.service.SignService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zack.zhang <br>
 * @create 2021-08-15 09:03:18 <br>
 * @project ware <br>
 */
@Service
public class SignServiceImpl extends ServiceImpl<SignMapper, SignEntity> implements SignService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SignEntity> page =
                this.page(
                        new CommonQuery<SignEntity>().getPage(params),
                        new QueryWrapper<SignEntity>());

        return new PageUtils(page);
    }
}
