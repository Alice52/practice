package custom.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import custom.model.dto.PhaseDTO;
import custom.model.entity.Phase;
import custom.model.vo.PhaseVO;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Validated
public interface PhaseService extends IService<Phase> {

    /**
     * 获取指定的阶段信息
     *
     * @param id   指定的阶段标识
     * @param type 标识具体活动
     * @return
     */
    PhaseVO getPhase(Long id, String type);

    /**
     * 更新指定的阶段信息
     *
     * @param phase 阶段信息
     * @return
     */
    Boolean updatePhase(@NotNull PhaseDTO phase);

    /**
     * 删除指定的阶段信息
     *
     * @param id 指定的阶段标识
     * @return
     */
    Boolean deletePhase(Long id);

    /**
     * 创建阶段信息
     *
     * @param phase
     * @return
     */
    Boolean createPhase(PhaseDTO phase);

    /**
     * 获取所有阶段信息
     *
     * @param type 标识具体活动
     * @return
     */
    List<PhaseVO> listPhases(String type);

    /**
     * Get Phase According By Condition.
     *
     * @param dto
     * @return
     */
    default Phase getByCondition(PhaseDTO dto) {
        LambdaQueryWrapper<Phase> queryWrapper = buildQueryWrapper();

        Optional.ofNullable(dto.getId()).ifPresent(t -> queryWrapper.eq(Phase::getId, dto.getId()));
        Optional.ofNullable(dto.getType())
                .ifPresent(t -> queryWrapper.eq(Phase::getType, dto.getType()));
        Optional.ofNullable(dto.getPhaseCode())
                .ifPresent(t -> queryWrapper.eq(Phase::getPhaseCode, dto.getPhaseCode()));
        Optional.ofNullable(dto.getPhaseName())
                .ifPresent(t -> queryWrapper.eq(Phase::getPhaseName, dto.getPhaseName()));

        queryWrapper.last("LIMIT 1");

        return this.getOne(queryWrapper);
    }

    /**
     * Build Query Wrapper.
     *
     * @return
     */
    default LambdaQueryWrapper<Phase> buildQueryWrapper() {
        return buildQueryWrapper(null);
    }

    /**
     * Build Query One Wrapper.
     *
     * @param type
     * @return
     */
    default LambdaQueryWrapper<Phase> buildQueryWrapper(String type) {
        LambdaQueryWrapper<Phase> queryWrapper = Wrappers.<Phase>query().lambda();
        Optional.ofNullable(type).ifPresent(t -> queryWrapper.eq(Phase::getType, type));

        return queryWrapper;
    }
}
