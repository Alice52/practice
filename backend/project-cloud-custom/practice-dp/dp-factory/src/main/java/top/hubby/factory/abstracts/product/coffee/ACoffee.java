package top.hubby.factory.abstracts.product.coffee;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-13 4:16 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class ACoffee extends Coffee {

    @Override
    public String getName() {
        return "美式咖啡";
    }
}
