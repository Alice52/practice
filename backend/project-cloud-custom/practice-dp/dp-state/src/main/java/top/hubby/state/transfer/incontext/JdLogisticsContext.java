package top.hubby.state.transfer.incontext;

import lombok.Data;

import java.util.Objects;

/**
 * @author zack <br>
 * @create 2022-11-23 23:07 <br>
 * @project practice-optimize <br>
 */
@Data
public class JdLogisticsContext {
    private LogisticsState logisticsState;

    public void doAction() {
        Objects.requireNonNull(logisticsState);
        logisticsState.doAction(this);
    }
}
