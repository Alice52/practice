package top.hubby.facroty.abstracts;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import top.hubby.factory.abstracts.factory.BrandADCFactory;
import top.hubby.factory.abstracts.factory.DCFactory;
import top.hubby.factory.abstracts.product.coffee.Coffee;
import top.hubby.factory.abstracts.product.dessert.Dessert;

/**
 * @author asd <br>
 * @create 2021-10-13 4:53 PM <br>
 * @project pattern <br>
 */
@Slf4j
public class AbsFactoryTests {

    @Test
    public void testAbsFactory() {
        DCFactory factory = new BrandADCFactory();
        // DCFactory factory = new BrandBDCFactory();
        Coffee coffee = factory.createCoffee();
        Dessert dessert = factory.createDessert();
        log.info("BrandADCFactory product: coffee: {}, dessert: {}", coffee, dessert);
    }
}
