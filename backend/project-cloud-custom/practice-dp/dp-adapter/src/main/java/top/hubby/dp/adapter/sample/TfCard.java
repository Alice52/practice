package top.hubby.dp.adapter.sample;

/**
 * @author zack <br/>
 * @create 2022-12-22 22:38 <br/>
 * @project practice-optimize <br/>
 */
public interface TfCard {
    //读取TF卡方法
    String readTF();

    /**
     * @author zack <br/>
     * @create 2022-12-22 22:38 <br/>
     * @project practice-optimize <br/>
     */
    class TfCardImpl implements TfCard {

        @Override
        public String readTF() {
            return "tf card read msg : hello word tf card";
        }
    }
}
