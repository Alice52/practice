package common.custom.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URI;

import javax.annotation.Resource;

import common.core.util.FileUtil;
import common.oss.constnats.enums.OssUploadTypeEnum;
import common.oss.context.OssContext;
import common.oss.service.OSSHander;
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
public class OssTestV2 {

    private String URL_PATH =
            "https://imgcache.qq.com/open_proj/proj_qcloud_v2/cvm/src/styles/img/sence1.png";
    @Resource private OssContext ossContext;

    @SneakyThrows
    @Test
    public void testUploadAliyun() throws FileNotFoundException {

        URI u = URI.create(URL_PATH);
        try (InputStream inputStream = u.toURL().openStream()) {
            File file = new File("45.png");
            FileUtil.copyInputStreamToFile(inputStream, file);
            OSSHander handler = ossContext.getHandler(OssUploadTypeEnum.aliyun);
            handler.upload("45.png", file, null);

            file.delete();
        }
    }

    @SneakyThrows
    @Test
    public void testUploadTencent() throws FileNotFoundException {

        URI u = URI.create(URL_PATH);
        try (InputStream inputStream = u.toURL().openStream()) {
            File file = new File("45.png");
            FileUtil.copyInputStreamToFile(inputStream, file);
            OSSHander handler = ossContext.getHandler(OssUploadTypeEnum.tencent);
            handler.upload("45.png", file, null);

            file.delete();
        }
    }
}
