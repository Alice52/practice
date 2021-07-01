package common.api.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author asd <br>
 * @create 2021-06-30 9:23 AM <br>
 * @project custom-upms-grpc <br>
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PlainEntity<T extends Model> extends Model<T> implements Serializable {
    protected static final long serialVersionUID = 1L;

    private Boolean isDeleted;

    private LocalDateTime insertedTime;

    private Long insertedBy;
}
