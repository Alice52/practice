package top.hubby.exception.static$;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author asd <br>
 * @create 2021-10-28 2:57 PM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class StaticExceptionTests {

    @Test
    public void wrong() {
        try {
            createOrderWrong();
        } catch (Exception ex) {
            log.error("createOrder got error", ex);
        }

        try {
            cancelOrderWrong();
        } catch (Exception ex) {
            log.error("cancelOrder got error", ex);
        }
    }

    @Test
    public void right() {
        try {
            createOrderRight();
        } catch (Exception ex) {
            log.error("createOrder got error", ex);
        }
        try {
            cancelOrderRight();
        } catch (Exception ex) {
            log.error("cancelOrder got error", ex);
        }
    }

    private void createOrderWrong() {
        throw Exceptions.ORDEREXISTS;
    }

    private void cancelOrderWrong() {
        throw Exceptions.ORDEREXISTS2;
    }

    private void createOrderRight() {
        throw Exceptions.orderExists();
    }

    private void cancelOrderRight() {
        throw Exceptions.orderExists2();
    }
}
