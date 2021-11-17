package custom.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import common.database.model.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2021-04-09 10:12 <br>
 * @project integration <br>
 */
@Data
@TableName("boot_cache_all_star_phase")
public class Phase extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String phaseCode;

    private String phaseName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String type;

    private String status;
}
