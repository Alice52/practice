package top.hubby.factory.abstracts.factory;

import lombok.extern.slf4j.Slf4j;
import top.hubby.factory.abstracts.product.coffee.ACoffee;
import top.hubby.factory.abstracts.product.coffee.Coffee;
import top.hubby.factory.abstracts.product.dessert.ADessert;
import top.hubby.factory.abstracts.product.dessert.Dessert;

/**
 * @author asd <br>
 * @create 2021-10-13 4:20 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class BrandADCFactory implements DCFactory {

    @Override
    public Coffee createCoffee() {
        return new ACoffee();
    }

    @Override
    public Dessert createDessert() {
        return new ADessert();
    }
}
