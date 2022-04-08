package custom.basic.api.feign.fallback;

import common.core.util.R;
import custom.basic.api.dto.CaptchaImageDTO;
import custom.basic.api.dto.SmsCodeDTO;
import custom.basic.api.feign.RemoteSmsCodeService;
import custom.basic.api.vo.SmsCodeVO;
import custom.basic.api.vo.SmsMemberVO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

/**
 * @author asd <br>
 * @create 2021-06-29 5:38 PM <br>
 * @project custom-upms-grpc <br>
 */
@Slf4j
@Component
public class RemoteSmsCodeServiceFallbackImpl implements RemoteSmsCodeService {
    @Setter private Throwable cause;

    @Override
    public R<SmsCodeVO> sendMessage(CaptchaImageDTO captchaImageDTO) {
        log.error("feign 发送短信失败:{}", captchaImageDTO, cause);
        return null;
    }

    @Override
    public R<SmsMemberVO> validateUser(SmsCodeDTO smsCodeDTO, String from) {
        log.error("feign 短信登录失败:{}", smsCodeDTO, cause);
        return new R<>();
    }
}
