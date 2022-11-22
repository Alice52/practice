package top.hubby.factory.config.product;

import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-13 3:10 PM <br>
 * @project pattern <br>
 */
@Slf4j
public abstract class Coffee {

    public abstract String getName();

    public void addsugar() {
        System.out.println("加糖");
    }

    public void addMilk() {
        System.out.println("加奶");
    }
}
