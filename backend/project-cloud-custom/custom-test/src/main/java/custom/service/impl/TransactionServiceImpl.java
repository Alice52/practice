package custom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import custom.converter.PhaseConverter;
import custom.mapper.PhaseMapper;
import custom.model.dto.PhaseDTO;
import custom.model.entity.Phase;
import custom.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author zack <br>
 * @create 2022-04-03 19:07 <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Service
public class TransactionServiceImpl extends ServiceImpl<PhaseMapper, Phase>
        implements TransactionService {

    @Override
    public boolean savePhase(PhaseDTO dto) {
        try {
            baseMapper.insert(PhaseConverter.CONVERTER.dto2po(dto));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            throw new RuntimeException("finally exception...");
        }
    }
}
