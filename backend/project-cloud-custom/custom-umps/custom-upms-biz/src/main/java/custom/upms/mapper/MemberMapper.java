package custom.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import custom.basic.api.entity.MemberEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@Mapper
public interface MemberMapper extends BaseMapper<MemberEntity> {}
