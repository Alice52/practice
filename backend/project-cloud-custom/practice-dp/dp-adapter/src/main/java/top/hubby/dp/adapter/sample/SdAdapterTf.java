package top.hubby.dp.adapter.sample;

/**
 * 类适配器模式违背了合成复用原则
 *
 * @author zack <br>
 * @create 2022-12-22 22:39 <br>
 * @project practice-optimize <br>
 */
@Deprecated
public class SdAdapterTf extends TfCard.TfCardImpl implements SdCard {

    @Override
    public String readSD() {
        System.out.println("adapter read tf card ");
        return readTF();
    }
}
