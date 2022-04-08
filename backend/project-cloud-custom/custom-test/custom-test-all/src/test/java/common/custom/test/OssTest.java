package common.custom.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import javax.annotation.Resource;

import common.core.util.FileUtil;
import common.oss.constnats.enums.OssUploadTypeEnum;
import common.oss.context.OssContextV2;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import top.hubby.test.custom.CustomApplication;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zack <br>
 * @create 2021-06-22 16:06 <br>
 * @project swagger-3 <br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomApplication.class)
public class OssTest {
    @Resource private OssContextV2 ossContext;

    private String URL_PATH =
            "https://imgcache.qq.com/open_proj/proj_qcloud_v2/cvm/src/styles/img/sence1.png";

    @SneakyThrows
    @Test
    public void testUploadAliyun() throws FileNotFoundException {

        URI u = URI.create(URL_PATH);
        try (InputStream inputStream = u.toURL().openStream()) {
            File file = new File("46.png");
            FileUtil.copyInputStreamToFile(inputStream, file);
            ossContext.upload("46.png", file, null, OssUploadTypeEnum.aliyun);

            file.delete();
        }
    }

    @SneakyThrows
    @Test
    public void testUploadTencent() throws FileNotFoundException {

        URI u = URI.create(URL_PATH);
        try (InputStream inputStream = u.toURL().openStream()) {
            File file = new File("46.png");
            FileUtil.copyInputStreamToFile(inputStream, file);
            ossContext.upload("46.png", file, null, OssUploadTypeEnum.tencent);

            file.delete();
        }
    }
}
