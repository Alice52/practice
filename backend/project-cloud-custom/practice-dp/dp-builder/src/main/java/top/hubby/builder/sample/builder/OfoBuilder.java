package top.hubby.builder.sample.builder;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zack <br>
 * @create 2021-09-15<br>
 * @project pattern <br>
 */
@Slf4j
public class OfoBuilder extends Builder {
    @Override
    public void buildFrame() {
        mBike.setFrame("碳纤维车架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("橡胶车座");
    }
}
