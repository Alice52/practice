package top.hubby.inherit.v0;

import lombok.extern.slf4j.Slf4j;

/**
 * 1. 鸵鸟: 不会飞的鸟还有很多，比如企鹅<br>
 * 2. 违背了迪米特法则: 暴露不该暴露的接口给外部, 增加了类使用过程中被误用的概率<br>
 * 3. 还有很多分类: 能不能下蛋, 会不会叫, 这些组合之后类就爆炸了, 所以并不是好的方式<br>
 *
 * @author asd <br>
 * @create 2021-12-10 10:42 AM <br>
 * @project pattern <br>
 */
@Slf4j
public class Ostrich extends AbstractBird {
    // ...省略其他属性和方法...
    @Override
    public void fly() {
        throw new UnsupportedOperationException("I can't fly.'");
    }
}
