package top.hubby.dp.proxy.dynamics.jdk;

import lombok.extern.slf4j.Slf4j;
import top.hubby.dp.proxy.statics.SellTickets;

/**
 * 火车站 火车站具有卖票功能，所以需要实现SellTickets接口
 *
 * @author zack <br>
 * @create 2022-12-22 16:03 <br>
 * @project practice-optimize <br>
 */
@Slf4j
public class TrainStation implements SellTickets {

    @Override
    public void sell() {
        log.info("火车站卖票");
    }
}
