package top.hubby.facroty.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.hubby.factory.config.factory.CoffeeFactory;
import top.hubby.factory.config.product.Coffee;

/**
 * @author asd <br>
 * @create 2021-10-13 4:40 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class CoffeeFactoryTests {

    @Test
    public void name() {
        Coffee coffee = CoffeeFactory.createCoffee("american");
        log.info(coffee.getName());

        Coffee latte = CoffeeFactory.createCoffee("latte");
        log.info(latte.getName());
    }
}
