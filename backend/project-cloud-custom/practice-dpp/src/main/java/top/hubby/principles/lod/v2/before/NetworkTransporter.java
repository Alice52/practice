package top.hubby.principles.lod.v2.before;

import lombok.extern.slf4j.Slf4j;
import top.hubby.principles.lod.v2.before.model.HtmlRequest;

/**
 *
 *
 * <pre>
 *     1. 这是一个底层接口, 应该具有通用性.
 *     2. 因此不应该依赖上层的数据结构: HtmlRequest
 * </pre>
 *
 * @author asd <br>
 * @create 2021-12-21 2:42 PM <br>
 * @project pattern <br>
 * @see NetworkTransporter
 */
@Slf4j
public class NetworkTransporter {
    public Byte[] send(HtmlRequest htmlRequest) {
        return null;
    }
}
