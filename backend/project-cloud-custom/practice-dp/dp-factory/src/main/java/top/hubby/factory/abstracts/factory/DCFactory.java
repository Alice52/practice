package top.hubby.factory.abstracts.factory;

import top.hubby.factory.abstracts.product.coffee.Coffee;
import top.hubby.factory.abstracts.product.dessert.Dessert;

/**
 * @author asd <br>
 * @create 2021-10-13 4:19 PM <br>
 * @project pattern <br>
 */
public interface DCFactory {

    // 生产咖啡的功能
    Coffee createCoffee();

    // 生产甜品的功能
    Dessert createDessert();
}
