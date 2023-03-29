package io.github.alice52.test.junit;

import com.alibaba.fastjson.JSON;
import io.github.alice52.test.component.IdGenerator;
import io.github.alice52.test.mapper.UserDAO;
import io.github.alice52.test.model.UserDO;
import io.github.alice52.test.model.UserVO;
import io.github.alice52.test.service.UserService;
import io.github.alice52.test.util.ResourceHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

/**
 * @author T04856 <br>
 * @create 2023-03-29 1:44 PM <br>
 * @project project-cloud-custom <br>
 */
// @RunWith(PowerMockRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    /** 模拟依赖对象: 用户DAO */
    @Mock private UserDAO userDAO;
    /** 模拟依赖对象: 标识生成器 */
    @Mock private IdGenerator idGenerator;

    /** 定义被测对象: 用户服务 */
    @InjectMocks private UserService userService;

    /** 在测试之前 */
    @Before
    public void beforeTest() {
        // 注入依赖对象
        Whitebox.setInternalState(userService, "canModify", Boolean.TRUE);
    }

    /** 测试: 创建用户-新 */
    @Test
    public void testCreateUserWithNew() {
        // 模拟依赖方法: userDAO.getByName
        Mockito.doReturn(null).when(userDAO).getIdByName(Mockito.anyString());
        // 模拟依赖方法: idGenerator.next
        Long userId = 1L;
        Mockito.doReturn(userId).when(idGenerator).next();

        // 调用被测方法
        String text = ResourceHelper.getResourceAsString("userCreateVO.json");
        UserVO userCreate = JSON.parseObject(text, UserVO.class);
        Assert.assertEquals("用户标识不一致", userId, userService.createUser(userCreate));

        // 验证依赖方法: userDAO.getByName
        Mockito.verify(userDAO).getIdByName(userCreate.getName());
        // 验证依赖方法: idGenerator.next
        Mockito.verify(idGenerator).next();
        // 验证依赖方法: userDAO.create
        ArgumentCaptor<UserDO> userCreateCaptor = ArgumentCaptor.forClass(UserDO.class);
        Mockito.verify(userDAO).create(userCreateCaptor.capture());
        text = ResourceHelper.getResourceAsString("userCreateDO.json");
        Assert.assertEquals("用户创建不一致", text, JSON.toJSONString(userCreateCaptor.getValue()));

        // 验证依赖对象
        Mockito.verifyNoMoreInteractions(idGenerator, userDAO);
    }

    /** 测试: 创建用户-旧 */
    @Test
    public void testCreateUserWithOld() {
        // 模拟依赖方法: userDAO.getByName
        Long userId = 1L;
        Mockito.doReturn(userId).when(userDAO).getIdByName(Mockito.anyString());

        // 调用被测方法
        String text = ResourceHelper.getResourceAsString("userCreateVO.json");
        UserVO userCreate = JSON.parseObject(text, UserVO.class);
        Assert.assertEquals("用户标识不一致", userId, userService.createUser(userCreate));

        // 验证依赖方法: userDAO.getByName
        Mockito.verify(userDAO).getIdByName(userCreate.getName());
        // 验证依赖方法: userDAO.modify
        ArgumentCaptor<UserDO> userModifyCaptor = ArgumentCaptor.forClass(UserDO.class);
        Mockito.verify(userDAO).modify(userModifyCaptor.capture());
        text = ResourceHelper.getResourceAsString("userModifyDO.json");
        Assert.assertEquals("用户修改不一致", text, JSON.toJSONString(userModifyCaptor.getValue()));

        // 验证依赖对象
        Mockito.verifyNoInteractions(idGenerator);
        Mockito.verifyNoMoreInteractions(userDAO);
    }

    /** 测试: 创建用户-异常 */
    @Test
    public void testCreateUserWithException() {
        // 注入依赖对象
        Whitebox.setInternalState(userService, "canModify", Boolean.FALSE);

        // 模拟依赖方法: userDAO.getByName
        Long userId = 1L;
        Mockito.doReturn(userId).when(userDAO).getIdByName(Mockito.anyString());

        // 调用被测方法
        String text = ResourceHelper.getResourceAsString("userCreateVO.json");
        UserVO userCreate = JSON.parseObject(text, UserVO.class);
        UnsupportedOperationException exception =
                Assert.assertThrows(
                        "返回异常不一致",
                        UnsupportedOperationException.class,
                        () -> userService.createUser(userCreate));
        // 验证依赖方法
    }
}
