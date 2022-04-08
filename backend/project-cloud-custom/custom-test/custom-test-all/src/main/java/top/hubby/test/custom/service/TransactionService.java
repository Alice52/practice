package top.hubby.test.custom.service;

import top.hubby.test.custom.model.dto.PhaseDTO;

/**
 * @author zack <br>
 * @create 2022-04-03 19:06 <br>
 * @project project-cloud-custom <br>
 */
public interface TransactionService {

    public boolean savePhase(PhaseDTO phase);
}
