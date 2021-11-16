package common.api.utils.xss;

/**
 * @author zack <br>
 * @create 2021-08-15<br>
 * @project project-cloud-custom <br>
 */
import cn.hutool.core.util.StrUtil;
import common.api.exception.CloudException;

import java.util.Arrays;

/**
 * filter sql str due to inject.
 *
 * @author zack <br>
 * @create 2020-10-05 15:35 <br>
 * @project project-ec <br>
 */
public class SQLfilter {

    /**
     * SQL inject
     *
     * @param str input string
     */
    public static String sqlInject(String str) {
        if (StrUtil.isBlank(str)) {
            return null;
        }

        // replace chars of ' | " | ; | \
        str =
                StrUtil.replace(str, "'", "")
                        .replace("\"", "")
                        .replace(";", "")
                        .replace("\\", "")
                        .toLowerCase();

        // invalid chars
        String[] keywords = {
            "master", "truncate", "insert", "select", "delete", "update", "declare", "alter", "drop"
        };

        // throw an exception if contain sql keywords
        String finalStr = str;
        Arrays.stream(keywords)
                .forEach(
                        x -> {
                            if (finalStr.indexOf(x) != -1) {
                                throw new CloudException("Contain invalid chars");
                            }
                        });

        return str;
    }
}
