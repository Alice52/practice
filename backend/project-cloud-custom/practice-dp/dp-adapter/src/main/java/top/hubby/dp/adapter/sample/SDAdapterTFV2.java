package top.hubby.dp.adapter.sample;

/**
 * @author zack <br/>
 * @create 2022-12-22 22:44 <br/>
 * @project practice-optimize <br/>
 */
public class SDAdapterTFV2 implements SdCard {
    private TfCard tfCard;

    public SDAdapterTFV2(TfCard tfCard) {
        this.tfCard = tfCard;
    }

    @Override
    public String readSD() {
        System.out.println("adapter read tf card ");
        return tfCard.readTF();
    }
}
