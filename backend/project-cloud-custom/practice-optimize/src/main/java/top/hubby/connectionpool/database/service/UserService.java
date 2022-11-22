package top.hubby.connectionpool.database.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.hubby.connectionpool.database.mapper.UserMapper;
import top.hubby.model.entity.User;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-10-22 10:18 AM <br>
 * @project swagger-3 <br>
 */
@Service
public class UserService {
    @Resource private UserMapper userMapper;

    @Transactional
    public User register() {
        User user = new User();
        user.setName("new-user-" + System.currentTimeMillis());
        userMapper.insert(user);
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Transactional
    public void createUser(User entity) {
        userMapper.insert(entity);
        if (entity.getName().contains("test")) {
            throw new RuntimeException("invalid username!");
        }
    }

    public Long getUserCount(String name) {

        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().eq(User::getName, name);

        return userMapper.selectCount(wrapper);
    }
}
