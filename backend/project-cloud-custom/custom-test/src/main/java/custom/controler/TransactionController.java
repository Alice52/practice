package custom.controler;

import common.core.util.R;
import custom.model.dto.PhaseDTO;
import custom.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zack <br>
 * @create 2022-04-03 19:04 <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

    @Resource private TransactionService transactionService;

    @PostMapping("/phase")
    public R<Boolean> create(@RequestBody PhaseDTO phase) {
        return R.success(transactionService.savePhase(phase));
    }
}
