package top.hubby.builder.sample.builder;

import lombok.extern.slf4j.Slf4j;
import top.hubby.builder.sample.product.Bike;

/**
 * @author zack <br>
 * @create 2021-09-15<br>
 * @project pattern <br>
 */
@Slf4j
public class MobikeBuilder extends Builder {
    @Override
    public void buildFrame() {
        mBike.setFrame("铝合金车架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("真皮车座");
    }

    @Override
    public Bike build() {
        return mBike;
    }
}
