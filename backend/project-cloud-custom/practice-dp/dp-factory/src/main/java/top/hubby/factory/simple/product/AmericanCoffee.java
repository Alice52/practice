package top.hubby.factory.simple.product;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-13 3:12 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class AmericanCoffee extends Coffee {

    @Override
    public String getName() {
        return "美式咖啡";
    }
}
