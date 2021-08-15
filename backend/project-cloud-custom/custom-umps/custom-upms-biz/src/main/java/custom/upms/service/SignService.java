package custom.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import common.api.utils.PageUtils;
import custom.basic.api.entity.SignEntity;

import java.util.Map;

/**
 * @author zack.zhang <br>
 * @create 2021-08-15 09:03:18 <br>
 * @project ware <br>
 */
public interface SignService extends IService<SignEntity> {

    PageUtils queryPage(Map<String, Object> params);
}
