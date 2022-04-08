package common.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author asd <br>
 * @create 2021-06-30 9:24 AM <br>
 * @project custom-upms-grpc <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SimpleEntity<T extends Model> extends Model implements Serializable {
    protected static final long serialVersionUID = 1L;

    private Boolean isDeleted;

    @TableField(value = "inserted_time", fill = FieldFill.INSERT)
    private LocalDateTime insertedTime;

    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private LocalDateTime updatedTime;
}
