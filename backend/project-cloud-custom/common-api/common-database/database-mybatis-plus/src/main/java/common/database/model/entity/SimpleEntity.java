package common.database.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class SimpleEntity<T extends Model> extends Model<T> implements Serializable {
    protected static final long serialVersionUID = 1L;

    @TableLogic
    private Boolean isDeleted;

    @TableField(value = "inserted_time", fill = FieldFill.INSERT)
    private LocalDateTime insertedTime;

    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private LocalDateTime updatedTime;
}
