package top.hubby.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;

/**
 * @author asd <br>
 * @create 2021-10-22 10:12 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
@Data
@ToString
@TableName("practice_user")
@NoArgsConstructor
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @TableField private String name;

    public User(String name) {
        this.name = name;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static void main(String[] args) {
        Hashtable objectTreeMap = new Hashtable<>();
        objectTreeMap.put(null, "null");
    }
}
