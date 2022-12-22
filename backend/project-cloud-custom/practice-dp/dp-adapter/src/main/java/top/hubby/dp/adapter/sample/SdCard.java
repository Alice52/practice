package top.hubby.dp.adapter.sample;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zack <br/>
 * @create 2022-12-22 22:36 <br/>
 * @project practice-optimize <br/>
 */
public interface SdCard {
    //读取SD卡方法
    String readSD();

    /**
     * @author zack <br>
     * @create 2022-12-22 22:37 <br>
     * @project practice-optimize <br>
     */
    @Slf4j
    class SdCardImpl implements SdCard {

        @Override
        public String readSD() {
            return "sd card read a msg :hello word SD";
        }
    }
}
