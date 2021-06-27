package custom.gateway.handler;


import cn.hutool.core.util.RandomUtil;
import com.google.code.kaptcha.Producer;
import common.redis.constants.enums.RedisKeyCommonEnum;
import common.redis.utils.RedisUtil;
import custom.gateway.constants.AuthConstants;
import custom.gateway.constants.CacheKeys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author zack <br/>
 * @create 2021-06-26<br/>
 * @project project-cloud-custom <br/>
 */
@Slf4j
@Component
public class CaptchaHandler implements HandlerFunction<ServerResponse> {

    @Resource
    private Producer kaptchaProducer;

    @Resource
    private RedisUtil redisUtils;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        //生成验证码
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);

        String captchaKeyParamName = AuthConstants.PARAM_CAPTCHA_KEY;
        String captchaKey = serverRequest.queryParam(captchaKeyParamName).orElse(RandomUtil.randomString(10));

        if (StringUtils.isBlank(captchaKey)) {
            return Mono.error(new MissingServletRequestParameterException(captchaKeyParamName, "String"));
        }

        redisUtils.set(RedisKeyCommonEnum.VERIFY_CODE, text, 10, TimeUnit.MINUTES, CacheKeys.CAPTCHA_CODE_KEY_PREFIX + captchaKey);

        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpeg", os);
        } catch (IOException e) {
            log.error("ImageIO write error", e);
            return Mono.error(e);
        }

        return ServerResponse
                .status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(BodyInserters.fromResource(new ImageResource(os.toByteArray())));
    }

    private class ImageResource extends ByteArrayResource {

        public ImageResource(byte[] byteArray) {
            super(byteArray);
        }

        @Override
        public String getFilename() {
            return "captcha.jpg";
        }
    }
}
