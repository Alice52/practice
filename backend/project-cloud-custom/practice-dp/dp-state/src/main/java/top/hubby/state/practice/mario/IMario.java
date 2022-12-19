package top.hubby.state.practice.mario;

import lombok.Data;
import top.hubby.state.practice.mario.v3.MarioStateMachineContext;

@Data
public abstract class IMario {
    // 定义一个环境角色，也就是封装状态的变化引起的功能变化
    protected MarioStateMachineContext context;

    public State getName() {
        return null;
    }

    public abstract void obtainMushRoom();

    public abstract void obtainCape();

    public abstract void obtainFireFlower();

    public abstract void meetMonster();
}
