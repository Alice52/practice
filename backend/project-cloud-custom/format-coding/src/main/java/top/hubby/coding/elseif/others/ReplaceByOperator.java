package top.hubby.coding.elseif.others;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public class ReplaceByOperator {

    private static final Logger LOG = LoggerFactory.getLogger(ReplaceByOperator.class);

    public static String getScoreLevel(Integer score) {
        String level;
        if (score > 90) {
            level = "A";
        } else {
            level = "B";
        }
        return level;
    }

    public static String getScoreLevelAfter(Integer score) {
        return score > 90 ? "A" : "B";
    }

    public static void main(String[] args) {
        LOG.info(getScoreLevel(85));
        LOG.info(getScoreLevelAfter(85));
    }
}
