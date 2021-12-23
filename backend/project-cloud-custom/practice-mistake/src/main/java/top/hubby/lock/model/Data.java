package top.hubby.lock.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author asd <br>
 * @create 2021-10-20 10:14 AM <br>
 * @project swagger-3 <br>
 */
@Slf4j
public class Data {
    @Getter private static int counter = 0;
    private static Object locker = new Object();

    public static int reset() {
        counter = 0;
        return counter;
    }

    // also can change to static method
    @Deprecated
    public synchronized void wrong() {
        counter++;
    }

    public void right() {
        synchronized (locker) {
            counter++;
        }
    }
}
