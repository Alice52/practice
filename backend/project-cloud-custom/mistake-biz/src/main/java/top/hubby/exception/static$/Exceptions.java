package top.hubby.exception.static$;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-28 2:56 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class Exceptions {
    public static BusinessException ORDEREXISTS = new BusinessException("订单已经存在", 3001);
    public static BusinessException ORDEREXISTS2 = new BusinessException("订单已经取消", 3002);

    public static BusinessException orderExists() {
        return new BusinessException("订单已经存在", 3001);
    }

    public static BusinessException orderExists2() {
        return new BusinessException("订单已经取消", 3002);
    }
}
