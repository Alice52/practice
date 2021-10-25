package top.hubby.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-22 10:12 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Data
@ToString
@TableName("practice_user")
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;
}
