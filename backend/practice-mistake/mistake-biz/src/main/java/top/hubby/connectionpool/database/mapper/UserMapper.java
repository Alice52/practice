package top.hubby.connectionpool.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.hubby.connectionpool.database.model.User;

/**
 * @author asd <br>
 * @create 2021-10-22 10:18 AM <br>
 * @project swagger-3 <br>
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {}
