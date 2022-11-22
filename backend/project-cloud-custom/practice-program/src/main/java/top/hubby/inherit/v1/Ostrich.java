package top.hubby.inherit.v1;

import lombok.extern.slf4j.Slf4j;

/**
 * 鸵鸟
 *
 * <pre>
 *     1. 解决了各种行为的组合导致的类爆炸的问题
 *     2. 但是下蛋行为都是一样的, 每个都要实现就会带来复杂度
 *          - 默认接口实现
 *          - 实现默认类, 之后组合使用
 * </pre>
 *
 * @author asd <br>
 * @create 2021-12-10 10:46 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class Ostrich implements Tweetable, EggLayable {

    // ... 省略其他属性和方法...
    @Override
    public void layEgg() {}

    @Override
    public void tweet() {}
}
