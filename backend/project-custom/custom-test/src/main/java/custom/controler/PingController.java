package custom.controler;

import common.core.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zack <br>
 * @create 2021-06-01 18:28 <br>
 * @project custom-test <br>
 */
@RestController
@RequestMapping("/health")
public class PingController {

    @GetMapping("/ping")
    public R<String> ping() {
        return R.<String>builder().data("pong").build();
    }
}
