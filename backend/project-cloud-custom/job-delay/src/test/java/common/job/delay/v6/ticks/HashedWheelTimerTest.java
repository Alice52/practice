package common.job.delay.v6.ticks;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author asd <br>
 * @create 2021-12-03 2:48 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
public class HashedWheelTimerTest {

    public static void main(String[] argv) {
        MyTimerTask timerTask = new MyTimerTask(true);
        Timer timer = new HashedWheelTimer();
        timer.newTimeout(timerTask, 5, TimeUnit.SECONDS);

        int i = 1;
        while (timerTask.flag) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(i + "秒过去了");
            i++;
        }
    }

    static class MyTimerTask implements TimerTask {
        boolean flag;

        public MyTimerTask(boolean flag) {
            this.flag = flag;
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println("要去数据库删除订单了。。。。");
            this.flag = false;
        }
    }
}
