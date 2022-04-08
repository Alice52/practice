package top.hubby.coding.elseif.executor.test;

import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.hubby.coding.elseif.executor.rule.BaseRule;
import top.hubby.coding.elseif.executor.service.RuleService;

import static top.hubby.coding.elseif.executor.RuleConstant.MATCH_NATIONALITY_START;

/**
 * @author asd <br>
 * @create 2021-12-27 3:19 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
public class RuleServiceTest {
    @Test
    public void execute() {
        // 1. 定义规则  init rule
        AddressRule addressRule = new AddressRule();

        // 2. 构造需要的数据 create dto
        DataDTO dto = new DataDTO();
        dto.setAge(5);
        dto.setAddress("北京");

        // 3. 通过以链式调用构建和执行 rule execute
        List<BaseRule<DataDTO>> ruleList = Collections.singletonList(addressRule);
        boolean ruleResult = RuleService.<DataDTO>create().and(ruleList).or(ruleList).execute(dto);
        log.info("this student rule execute result :" + ruleResult);
    }

    @Test
    @Deprecated
    public void executeV2() {
        // 1. 定义规则  init rule
        // 2. 构造需要的数据 create dto
        DataDTO dto = new DataDTO();
        dto.setAge(5);
        dto.setAddress("北京");

        // 3. 通过以链式调用构建和执行 rule execute
        boolean ruleResult =
                RuleService.<DataDTO>create()
                        .and(x -> dto.getAddress().startsWith(MATCH_NATIONALITY_START))
                        .or()
                        .execute(dto);
        log.info("this student rule execute result :" + ruleResult);
    }
}
