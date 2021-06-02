package custom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import custom.constants.enums.ActivityPhaseEnum;
import custom.mapper.PhaseMapper;
import custom.model.dto.PhaseDTO;
import custom.model.entity.Phase;
import custom.model.vo.PhaseVO;
import custom.service.ActivityService;
import custom.service.PhaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author zack <br>
 * @create 2021-04-09 10:22 <br>
 * @project integration <br>
 */
@Service
public class PhaseServiceImpl extends ServiceImpl<PhaseMapper, Phase> implements PhaseService {

    @Resource private ActivityService activityService;

    private static LambdaQueryWrapper<Phase> buildQueryWrapper() {
        return buildQueryWrapper(null);
    }

    private static LambdaQueryWrapper<Phase> buildQueryWrapper(String type) {
        LambdaQueryWrapper<Phase> queryWrapper = Wrappers.<Phase>query().lambda();
        Optional.ofNullable(type).ifPresent(t -> queryWrapper.eq(Phase::getType, type));

        return queryWrapper;
    }

    @Override
    public PhaseVO getPhase(Long id, String type) {

        Phase phase = getByCondition(new PhaseDTO(id, type));
        PhaseVO phaseVO = new PhaseVO();
        BeanUtil.copyProperties(phase, phaseVO, "status");

        return phaseVO;
    }

    @Override
    public Boolean updatePhase(PhaseDTO dto) {
        validateDuplicateByCodeOrName(dto);

        Phase phase = new Phase();
        BeanUtil.copyProperties(dto, phase, "status");

        return retBool(baseMapper.updateById(phase));
    }

    @Override
    public Boolean deletePhase(Long id) {

        ensureNoUse(id);
        Phase phase = validateThenGet(id);
        phase.setIsDeleted(true);

        return retBool(baseMapper.updateById(phase));
    }

    @Override
    public Boolean createPhase(PhaseDTO dto) {

        validateDuplicateByCodeOrName(dto);

        Phase phase = new Phase();
        BeanUtil.copyProperties(dto, phase, "status");

        return retBool(baseMapper.insert(phase));
    }

    @Override
    public List<PhaseVO> listPhases(String type) {

        return getPhases(type);
    }

    private void ensureNoUse(Long id) {

        if (activityService.queryByPhaseIds(Arrays.asList(id)).size() > 0) {
            throw new RuntimeException("不能删除已使用的阶段");
        }
    }

    private List<PhaseVO> getPhases(String type) {
        LambdaQueryWrapper<Phase> queryWrapper = buildQueryWrapper(type);
        List<Phase> phases = this.list(queryWrapper);

        return phases.stream().map(PhaseVO::new).collect(Collectors.toList());
    }

    private void validateDuplicateByCodeOrName(PhaseDTO dto) {

        ensureValidPhaseCode(dto.getPhaseCode());

        LambdaQueryWrapper<Phase> queryWrapper = buildQueryWrapper();
        queryWrapper.and(
                obj ->
                        obj.eq(Phase::getPhaseCode, dto.getPhaseCode())
                                .or()
                                .eq(Phase::getPhaseName, dto.getPhaseName()));

        if (ObjectUtil.isNotNull(dto.getId())) {
            queryWrapper.ne(Phase::getId, dto.getId());
        }

        List<Phase> phases = this.list(queryWrapper);

        if (!CollUtil.isEmpty(phases)) {
            throw new RuntimeException("阶段Code或者阶段名称重复");
        }
    }

    private void ensureValidPhaseCode(String phaseCode) {
        if (StrUtil.isNotEmpty(phaseCode)
                && Boolean.FALSE.equals(ActivityPhaseEnum.contains(phaseCode))) {
            throw new RuntimeException("不是有效的阶段 Code");
        }
    }

    private Phase validateThenGet(Long id) {
        return validateThenGet(id, null);
    }

    /**
     * 根据条件判断记录是否存在, 不存在则抛出 {@link RuntimeException}, 存在则获取该对象
     *
     * @param id
     * @param type
     * @return
     */
    private Phase validateThenGet(Long id, String type) {

        Phase phase = getByCondition(new PhaseDTO(id, type));
        Optional.ofNullable(phase)
                .orElseThrow(() -> new RuntimeException(StrUtil.format("Id 为 {} 的记录不存在", id)));

        return phase;
    }

    private Phase getByCondition(PhaseDTO dto) {
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
}
