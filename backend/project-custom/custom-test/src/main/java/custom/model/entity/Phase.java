package custom.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2021-04-09 10:12 <br>
 * @project integration <br>
 */
@Data
@TableName("boot_cache_all_star_phase")
public class Phase extends BaseEntity<Phase> {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 阶段编码 */
    private String phaseCode;

    /** 阶段名称 */
    private String phaseName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    /** 阶段类型， 例如年度，月度， 2021年度 */
    private String type;

    /** 阶段状态 */
    private String status;
}
