package top.hubby.dp.adapter.sample;

/**
 * @author zack <br>
 * @create 2022-12-22 22:40 <br>
 * @project practice-optimize <br>
 */
public class ClientTest {
    public static void main(String[] args) {
        Computer computer = new Computer();

        SdCard sdCard = new SdCard.SdCardImpl();
        System.out.println(computer.readSD(sdCard));

        System.out.println("------------");
        SdAdapterTf adapter = new SdAdapterTf();
        System.out.println(computer.readSD(adapter));

        System.out.println("------------");
        TfCard tfCard = new TfCard.TfCardImpl();
        SDAdapterTFV2 adapter2 = new SDAdapterTFV2(tfCard);
        System.out.println(computer.readSD(adapter2));
    }
}
