package top.hubby.builder.sample.builder;

import lombok.extern.slf4j.Slf4j;
import top.hubby.builder.sample.product.Bike;

/**
 * @author zack <br>
 * @create 2021-09-15<br>
 * @project pattern <br>
 */
@Slf4j
public abstract class Builder {

    protected Bike mBike = new Bike();

    public abstract void buildFrame();

    public abstract void buildSeat();

    public Bike build() {
        return mBike;
    }
}
