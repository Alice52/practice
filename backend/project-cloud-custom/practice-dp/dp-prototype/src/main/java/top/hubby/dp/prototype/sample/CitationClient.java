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

        c1.setAge(17);
        award.setPrice(19);

        // string is ref type and this equals is true due to string-constant-pool
        log.info("c1.getName()= c2.getName(): {}", c1.getName() == c2.getName());
        log.info("c1.getAge() != c2.getAge(): {}", c1.getAge() != c2.getAge());
        // this equals is true due tu award is ref type
        log.info("c1.getAward() = c2.getAward(): {}", c1.getAward() == c2.getAward());
        log.info("c1's award[{}], c2's award[{}]", c1.getAward().price, c2.getAward().price);
    }
}
