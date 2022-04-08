package top.hubby.coding.elseif.strategy2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zack <br>
 * @create 2020-12-29<br>
 * @project common-coding <br>
 */
public class MapFunctional {

    private static final Logger LOG = LoggerFactory.getLogger(MapFunctional.class);
    /** After: and can customize key strategy for complex logic */
    private static Map<String, Function<String, String>> checkResultDispatcher =
            new HashMap<String, Function<String, String>>(3) {
                {
                    put("check1", order -> "1");
                    put("check2", order -> "2");
                    put("check3", order -> "3");
                }
            };

    public static String getCheckResultBefore(String order) {
        if ("check1".equals(order)) {
            return "1";
        }
        if ("check2".equals(order)) {
            return "2";
        }

        if ("check3".equals(order)) {
            return "3";
        }

        return StrUtil.EMPTY;
    }

    public static String getCheckResultSuper(String order) {
        Function<String, String> result = checkResultDispatcher.get(order);
        if (result != null) {
            return result.apply(order);
        }

        return StrUtil.EMPTY;
    }

    public static void main(String[] args) {
        LOG.info("{}", getCheckResultBefore("check1"));
        LOG.info("{}", getCheckResultSuper("check1"));
    }
}
