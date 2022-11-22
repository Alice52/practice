package top.hubby.pattern.builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.hubby.builder.sample.Director;
import top.hubby.builder.sample.builder.OfoBuilder;
import top.hubby.builder.sample.product.Bike;

/**
 * @author zack <br>
 * @create 2021-09-15<br>
 * @project pattern <br>
 */
@Slf4j
public class BuilderTests {

    @Test
    public void testBuilder() {
        OfoBuilder builder = new OfoBuilder();
        Director director = new Director(builder);
        Bike bikeProduct = director.construct();
        log.info(bikeProduct.getFrame());
        log.info(bikeProduct.getSeat());
    }
}
