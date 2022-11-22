package top.hubby.factory.method.factory;

import lombok.extern.slf4j.Slf4j;
import top.hubby.factory.method.product.AmericanCoffee;
import top.hubby.factory.method.product.Coffee;

/**
 * @author asd <br>
 * @create 2021-10-13 3:35 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class AmericanCoffeeFactory implements CoffeeFactory {

    @Override
    public Coffee createCoffee() {
        return new AmericanCoffee();
    }
}
