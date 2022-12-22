package top.hubby.dp.prototype.practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zack <br>
 * @create 2022-12-22 10:39 <br>
 * @project practice-optimize <br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchWord {

    public String keyword;

    public Long lastUpdateTime;

    public Long count;
}
