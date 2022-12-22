package top.hubby.dp.adapter.sample;

/**
 * @author zack <br/>
 * @create 2022-12-22 22:37 <br/>
 * @project practice-optimize <br/>
 */
public class Computer {

    public String readSD(SdCard sdCard) {
        if(sdCard == null) {
            throw new NullPointerException("sd card null");
        }
        return sdCard.readSD();
    }
}
