package top.hubby.principles.lod;

import lombok.Data;

/**
 * @author zack <br>
 * @create 2021-09-13<br>
 * @project pattern <br>
 */
@Data
public class Agent {
    private Star star;
    private Fans fans;
    private Company company;

    public void meeting() {
        System.out.println(fans.getName() + "与明星" + star.getName() + "见面了");
    }

    public void business() {
        System.out.println(company.getName() + "与明星" + star.getName() + "洽淡业务。");
    }
}
