package top.hubby.dp.prototype.sample;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zack <br>
 * @create 2022-12-21 23:54 <br>
 * @project practice-optimize <br>
 */
@Slf4j
public class CitationClient {
    public static void main(String[] args) {

        Citation.Award award = new Citation.Award(15);
        Citation c1 = new Citation();
        c1.setName("张三");
        c1.setAge(18);
        c1.setAward(award);

        Citation c2 = c1.clone();
        c2.setName("李四");

        c1.setAge(17);
        award.setPrice(19);

        log.info("c1.getName().equals(c2.getName()): {}", c1.getName().equals(c2.getName()));
        log.info("c1.getAge() != c2.getAge(): {}", c1.getAge() != c2.getAge());
        log.info("c1.getAward().equal(c2.getAward()): {}", c1.getAward().canEqual(c2.getAward()));
        log.info("c1's award[{}], c2's award[{}]", c1.getAward().price, c2.getAward().price);
    }
}
