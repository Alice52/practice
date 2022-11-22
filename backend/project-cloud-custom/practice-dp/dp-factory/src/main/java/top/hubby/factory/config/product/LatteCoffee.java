package top.hubby.factory.config.product;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-13 3:11 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class LatteCoffee extends Coffee {

    @Override
    public String getName() {
        return "拿铁咖啡";
    }
}
