package top.hubby.dp.prototype.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author zack <br>
 * @create 2022-12-21 23:52 <br>
 * @project practice-optimize <br>
 */
@Data
public class Citation implements Cloneable {
    private String name;
    private int age;
    private Award award;

    @SneakyThrows
    @Override
    public Citation clone() {
        return (Citation) super.clone();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Award {
        int price;
    }
}
