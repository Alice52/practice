package top.hubby.facroty.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.hubby.factory.simple.factory.SimpleCoffeeFactory;
import top.hubby.factory.simple.factory.StaticSimpleCoffeeFactory;
import top.hubby.factory.simple.product.Coffee;

/**
 * @author asd <br>
 * @create 2021-10-13 3:13 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class CoffeeStoreTests {

    @Test
    public void testSimpleFactory() {

        SimpleCoffeeFactory factory = new SimpleCoffeeFactory();
        Coffee coffee = factory.createCoffee("americano");
        coffee.addMilk();
        coffee.addsugar();

        log.info("coffee: {}", coffee);
    }

    @Test
    public void testSimpleFactoryStatic() {

        Coffee coffee = StaticSimpleCoffeeFactory.createCoffee("americano");
        coffee.addMilk();
        coffee.addsugar();

        log.info("coffee: {}", coffee);
    }
}
