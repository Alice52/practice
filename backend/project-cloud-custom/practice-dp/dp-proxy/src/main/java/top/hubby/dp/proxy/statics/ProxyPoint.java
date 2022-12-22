package top.hubby.dp.proxy.statics;

import lombok.extern.slf4j.Slf4j;

/**代售点
 * @author zack <br/>
 * @create 2022-12-22 16:04 <br/>
 * @project practice-optimize <br/>
 */
@Slf4j
public class ProxyPoint implements SellTickets {
    private TrainStation station = new TrainStation();

    @Override
    public void sell() {
        log.info("do extension logic");
        station.sell();
    }
}