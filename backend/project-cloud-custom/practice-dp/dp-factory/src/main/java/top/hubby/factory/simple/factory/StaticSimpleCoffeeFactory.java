package top.hubby.factory.simple.factory;

import lombok.extern.slf4j.Slf4j;
import top.hubby.factory.simple.product.AmericanCoffee;
import top.hubby.factory.simple.product.Coffee;
import top.hubby.factory.simple.product.LatteCoffee;

/**
 * @author asd <br>
 * @create 2021-10-13 3:24 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class StaticSimpleCoffeeFactory {
    public static Coffee createCoffee(String type) {
        if ("americano".equals(type)) {
            return new AmericanCoffee();
        }

        if ("latte".equals(type)) {
            return new LatteCoffee();
        }
        return null;
    }
}
