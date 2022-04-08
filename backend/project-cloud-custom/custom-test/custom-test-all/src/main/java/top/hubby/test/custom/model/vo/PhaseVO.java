package top.hubby.test.custom.model.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import common.core.annotation.DeSensitive;
import common.core.annotation.discriptor.SensitiveStrategy;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.hubby.test.custom.constants.enums.PhaseStatusEnum;

/**
 * @author zack <br>
 * @create 2021-04-09 10:15 <br>
 * @project integration <br>
 */
@Data
@NoArgsConstructor
public class PhaseVO implements Serializable {
    private Long id;

    @ApiModelProperty("阶段 Code")
    private String phaseCode;

    @DeSensitive(strategy = SensitiveStrategy.USERNAME)
    @ApiModelProperty("阶段名称")
    private String phaseName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String type;

    private PhaseStatusEnum status;

    private Boolean isDeleted;

    private LocalDateTime insertedTime;

    private LocalDateTime updatedTime;

    private Long insertedBy;

    private Long updatedBy;

    @JsonProperty("allStarActivities")
    private List<ActivityVO> allStarActivityVO;

    public PhaseVO(String phaseCode, String phaseName) {
        this.phaseCode = phaseCode;
        this.phaseName = phaseName;
    }
}
