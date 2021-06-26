package common.core.util;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zack <br>
 * @create 2021-06-08 14:51 <br>
 * @project custom-test <br>
 */
@Slf4j
public final class ReqDeDupUtil {

    /**
     * 计算请求参数的 MD5.
     *
     * <pre>
     *     1. MD5 理论上可能会重复, 但是去重通常是短时间窗口内的去重[例如一秒]
     *     2. 一个短时间内同一个用户同样的接口能拼出不同的参数导致一样的 MD5 几乎是不可能的
     * </pre>
     *
     * @param request
     * @param excludeKeys
     * @return
     */
    public static String deDupParamMD5(HttpServletRequest request, String... excludeKeys) {

        Map<String, String[]> paramMap = request.getParameterMap();
        if (excludeKeys != null) {
            List<String> dedupExcludeKeys = Arrays.asList(excludeKeys);
            if (!dedupExcludeKeys.isEmpty()) {
                for (String dedupExcludeKey : dedupExcludeKeys) {
                    paramMap.remove(dedupExcludeKey);
                }
            }
        }

        paramMap.put("username", new String[]{WebUtil.getCurrentToken()});
        paramMap.put("uri", new String[]{request.getRequestURI()});
        String md5deDupParam = jdkMD5(JSONUtil.toJsonStr(paramMap));

        log.debug("md5deDupParam = {}, excludeKeys = {} {}", md5deDupParam, excludeKeys, paramMap);

        return md5deDupParam;
    }

    private static String jdkMD5(String src) {
        String res = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] mdBytes = messageDigest.digest(src.getBytes());
            res = DatatypeConverter.printHexBinary(mdBytes);
        } catch (Exception e) {
            log.error("", e);
        }
        return res;
    }
}
