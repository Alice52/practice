package top.hubby.factory.abstracts.factory;

import lombok.extern.slf4j.Slf4j;
import top.hubby.factory.abstracts.product.coffee.BCoffee;
import top.hubby.factory.abstracts.product.coffee.Coffee;
import top.hubby.factory.abstracts.product.dessert.BDessert;
import top.hubby.factory.abstracts.product.dessert.Dessert;

/**
 * @author asd <br>
 * @create 2021-10-13 4:22 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class BrandBDCFactory implements DCFactory {

    @Override
    public Coffee createCoffee() {
        return new BCoffee();
    }

    @Override
    public Dessert createDessert() {
        return new BDessert();
    }
}
