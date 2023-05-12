package top.hubby.coding.effective3.builder;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import static top.hubby.coding.effective3.builder.NyPizza.Size.SMALL;
import static top.hubby.coding.effective3.builder.Pizza.Topping.*;

/**
 * @author T04856 <br>
 * @create 2023-03-31 11:01 AM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
public class Test {
    private static long sum() {
        long sum = 0L;
        for (long i = 0; i <= Integer.MAX_VALUE; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {

        sum();

        NyPizza pizza = new NyPizza.Builder(SMALL).addTopping(SAUSAGE).addTopping(ONION).build();
        log.info(JSONUtil.toJsonStr(pizza));

        Calzone calzone = new Calzone.Builder().addTopping(HAM).sauceInside().build();
        log.info(JSONUtil.toJsonStr(calzone));
    }
}
