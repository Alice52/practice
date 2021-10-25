package top.hubby.connectionpool.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.hubby.connectionpool.database.mapper.UserMapper;
import top.hubby.model.entity.User;

import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-10-22 10:18 AM <br>
 * @project swagger-3 <br>
 */
@Service
public class UserService {
    @Autowired private UserMapper userMapper;

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
}
