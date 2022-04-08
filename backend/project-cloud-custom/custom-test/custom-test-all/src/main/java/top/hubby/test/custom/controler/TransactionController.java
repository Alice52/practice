package top.hubby.test.custom.controler;

import javax.annotation.Resource;

import common.core.util.R;
import lombok.extern.slf4j.Slf4j;
import top.hubby.test.custom.model.dto.PhaseDTO;
import top.hubby.test.custom.service.TransactionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
