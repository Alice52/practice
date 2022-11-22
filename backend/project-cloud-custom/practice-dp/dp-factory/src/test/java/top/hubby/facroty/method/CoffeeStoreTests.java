package top.hubby.facroty.method;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.hubby.factory.method.factory.CoffeeFactory;
import top.hubby.factory.method.factory.LatteCoffeeFactory;
import top.hubby.factory.method.product.Coffee;

/**
 * @author asd <br>
 * @create 2021-10-13 3:43 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class CoffeeStoreTests {
    @Test
    public void testSimpleFactory() {

        // CoffeeFactory factory = new AmericanCoffeeFactory();
        CoffeeFactory factory = new LatteCoffeeFactory();

        // 点咖啡
        Coffee coffee = factory.createCoffee();

        System.out.println(coffee.getName());
    }
}
