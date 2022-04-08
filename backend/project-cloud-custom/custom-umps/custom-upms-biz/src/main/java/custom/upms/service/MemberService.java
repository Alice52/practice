package custom.upms.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import common.api.utils.PageUtils;
import custom.basic.api.entity.MemberEntity;

/**
 * @author zack.zhang <br>
 * @create 2021-08-15 09:03:18 <br>
 * @project ware <br>
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
