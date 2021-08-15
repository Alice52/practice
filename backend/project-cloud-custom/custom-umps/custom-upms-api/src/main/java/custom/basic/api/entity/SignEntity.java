package custom.basic.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import common.api.model.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
@Data
@TableName("upms_sign")
public class SignEntity extends BaseEntity<SignEntity> {
    private static final long serialVersionUID = 1L;

    @TableId private Long id;
    private Long memberId;
    private LocalDateTime signDate;
    private Integer amount;
    @TableLogic private Boolean isDeleted;
}
