package common.custom.test;

import common.oss.constnats.enums.OssUploadTypeEnum;
import common.oss.context.OssContextV2;
import custom.CustomApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author zack <br>
 * @create 2021-06-22 16:06 <br>
 * @project swagger-3 <br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomApplication.class)
public class OssTestV2 {
    @Resource private OssContextV2 ossContext;

    @Test
    public void testUpload() throws FileNotFoundException {

        File tempFile = new File("C:\\Users\\asd\\Desktop\\algorithms\\45.png");

        ossContext.upload("45.png", tempFile, null, OssUploadTypeEnum.aliyun);
    }
}
