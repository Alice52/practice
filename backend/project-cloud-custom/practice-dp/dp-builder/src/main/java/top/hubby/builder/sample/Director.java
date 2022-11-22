package top.hubby.builder.sample;

import lombok.extern.slf4j.Slf4j;
import top.hubby.builder.sample.builder.Builder;
import top.hubby.builder.sample.product.Bike;

/**
 * @author zack <br>
 * @create 2021-09-15<br>
 * @project pattern <br>
 */
@Slf4j
public class Director {
    private Builder mBuilder;

    public Director(Builder builder) {
        mBuilder = builder;
    }

    public Bike construct() {
        mBuilder.buildFrame();
        mBuilder.buildSeat();
        return mBuilder.build();
    }
}
