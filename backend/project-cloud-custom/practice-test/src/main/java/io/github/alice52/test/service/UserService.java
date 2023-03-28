package io.github.alice52.test.service;

import io.github.alice52.test.component.IdGenerator;
import io.github.alice52.test.mapper.UserDAO;
import io.github.alice52.test.model.UserDO;
import io.github.alice52.test.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author T04856 <br>
 * @create 2023-03-28 4:06 PM <br>
 * @project project-cloud-custom <br>
 */
@Service
public class UserService {
    /** 服务相关: 用户DAO */
    @Autowired private UserDAO userDAO;
    /** 标识生成器 */
    @Autowired private IdGenerator idGenerator;

    /** 参数相关: 可以修改 */
    @Value("${userService.canModify:false}")
    private Boolean canModify;

    /**
     * 创建用户
     *
     * @param userCreate 用户创建
     * @return 用户标识
     */
    public Long createUser(UserVO userCreate) {
        // 获取用户标识
        Long userId = userDAO.getIdByName(userCreate.getName());

        // 根据存在处理
        // 根据存在处理: 不存在则创建
        if (Objects.isNull(userId)) {
            userId = idGenerator.next();
            UserDO create = new UserDO();
            create.setId(userId);
            create.setName(userCreate.getName());
            userDAO.create(create);
        }
        // 根据存在处理: 已存在可修改
        else if (Boolean.TRUE.equals(canModify)) {
            UserDO modify = new UserDO();
            modify.setId(userId);
            modify.setName(userCreate.getName());
            userDAO.modify(modify);
        }
        // 根据存在处理: 已存在禁修改
        else {
            throw new UnsupportedOperationException("不支持修改");
        }

        // 返回用户标识
        return userId;
    }
}
