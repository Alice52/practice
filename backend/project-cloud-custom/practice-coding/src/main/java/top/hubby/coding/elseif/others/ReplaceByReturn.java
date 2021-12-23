package top.hubby.coding.elseif.others;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public class ReplaceByReturn {
    private static final Logger LOG = LoggerFactory.getLogger(ReplaceByReturn.class);
    static int input = 10;

    @Test
    public void before() {

        if (input > 5) {
            LOG.info("do in if");
        } else {
            // do else
            LOG.info("do in else");
        }
    }

    @Test
    public void after() {

        if (input > 5) {
            LOG.info("do in if");
            return;
        }
        // do else
        LOG.info("do in else");
    }
}
