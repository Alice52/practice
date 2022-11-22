package top.hubby.factory.method.factory;

import lombok.extern.slf4j.Slf4j;
import top.hubby.factory.method.product.Coffee;
import top.hubby.factory.method.product.LatteCoffee;

/**
 * @author asd <br>
 * @create 2021-10-13 3:35 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class LatteCoffeeFactory implements CoffeeFactory {

    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
