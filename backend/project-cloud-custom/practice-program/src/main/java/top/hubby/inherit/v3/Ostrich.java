package top.hubby.inherit.v3;

import lombok.extern.slf4j.Slf4j;
import top.hubby.inherit.v1.Tweetable;

/**
 * 鸵鸟: 题干组合实现了默认实现
 *
 * @author asd <br>
 * @create 2021-12-10 10:51 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class Ostrich implements Tweetable, Flyable {
    // 组合
    private TweetAbility tweetAbility = new TweetAbility();
    private FlyAbility flyAbility = new FlyAbility();

    @Override
    public void tweet() {
        tweetAbility.tweet(); // 委托
    }

    @Override
    public void fly() {
        flyAbility.fly();
    }
}
