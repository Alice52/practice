package top.hubby.coding.elseif.others;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zack <br>
 * @create 2020-12-28<br>
 * @project common-coding <br>
 */
public class ReplaceByMap {

    private static final Logger LOG = LoggerFactory.getLogger(ReplaceByMap.class);
    /**
     * Before optimize.
     *
     * @param input
     * @return
     */
    public static String getTypeBefore(int input) {
        String type = StrUtil.EMPTY;
        if (input == 1) {
            type = "name";
        } else if (input == 2) {
            type = "id";
        } else if (input == 3) {
            type = "mobile";
        }

        return type;
    }

    public static String getTypeAfter(int input) {

        Map<Integer, String> typeMap =
                new HashMap<Integer, String>(3) {
                    {
                        put(1, "name");
                        put(2, "id");
                        put(3, "mobile");
                    }
                };

        return Optional.ofNullable(typeMap.get(input)).orElse(StrUtil.EMPTY);
    }

    public static void main(String[] args) {
        LOG.info(getTypeBefore(5));
        LOG.info(getTypeAfter(5));
    }
}
