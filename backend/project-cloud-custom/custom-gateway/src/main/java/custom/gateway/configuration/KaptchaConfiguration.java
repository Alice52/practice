package custom.gateway.configuration;

/**
 * @author zack <br>
 * @create 2021-06-26<br>
 * @project project-cloud-custom <br>
 */
import java.util.Properties;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "common.core.kaptcha")
@Setter
public class KaptchaConfiguration {
    /** 图片宽度 */
    private static final int DEFAULT_IMAGE_WIDTH = 100;

    /** 图片高度 */
    private static final int DEFAULT_IMAGE_HEIGHT = 40;

    /** 验证码长度 */
    private static final int DEFAULT_CHAR_LENGTH = 4;

    /** 文字间隔 */
    private static final int DEFAULT_CHAR_SPACE = 5;

    /** 字体颜色, 合法值: r,g,b[,a] 或者 white,black,blue. */
    private static final String DEFAULT_FONT_COLOR = "black";

    /** 文字大小 */
    private static final int DEFAULT_FONT_SIZE = 30;

    /** 图片边框, yes|no */
    private static final String DEFAULT_IMAGE_BORDER = "no";

    /** 实现干扰类 */
    private static final String DEFAULT_NOISE_IMPL = "com.google.code.kaptcha.impl.NoNoise";

    private int width = DEFAULT_IMAGE_WIDTH;
    private int height = DEFAULT_IMAGE_HEIGHT;

    private int charLength = DEFAULT_CHAR_LENGTH;
    private int charSpace = DEFAULT_CHAR_SPACE;

    private String fontColor = DEFAULT_FONT_COLOR;
    private int fontSize = DEFAULT_FONT_SIZE;

    @Bean
    public DefaultKaptcha kaptchaProducer() {
        Properties properties = new Properties();
        properties.put(Constants.KAPTCHA_BORDER, DEFAULT_IMAGE_BORDER);
        properties.put(Constants.KAPTCHA_IMAGE_WIDTH, String.valueOf(this.width));
        properties.put(Constants.KAPTCHA_IMAGE_HEIGHT, String.valueOf(this.height));
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, this.fontColor);
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, String.valueOf(this.fontSize));
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, String.valueOf(this.charLength));
        properties.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, String.valueOf(this.charSpace));
        properties.put(Constants.KAPTCHA_NOISE_IMPL, DEFAULT_NOISE_IMPL);
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
