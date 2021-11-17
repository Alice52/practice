package custom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import custom.model.entity.Phase;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zack <br>
 * @create 2021-04-09 10:23 <br>
 * @project integration <br>
 */
@Mapper
public interface PhaseMapper extends BaseMapper<Phase> {}
