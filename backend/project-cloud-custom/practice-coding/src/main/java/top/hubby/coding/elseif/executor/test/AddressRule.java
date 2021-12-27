package top.hubby.coding.elseif.executor.test;

import lombok.extern.slf4j.Slf4j;
import top.hubby.coding.elseif.executor.rule.AbstractRule;

import static top.hubby.coding.elseif.executor.RuleConstant.MATCH_NATIONALITY_START;

/**
 * @author asd <br>
 * @create 2021-12-27 3:18 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
public class AddressRule extends AbstractRule<DataDTO> {

    @Override
    public boolean execute(DataDTO dto) {
        log.info("AddressRule invoke!");
        if (dto.getAddress().startsWith(MATCH_NATIONALITY_START)) {
            return true;
        }

        return false;
    }
}
