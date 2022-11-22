package top.hubby.factory.abstracts.product.dessert;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-13 4:19 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class BDessert extends Dessert {
    @Override
    public void show() {
        System.out.println("提拉米苏");
    }
}
