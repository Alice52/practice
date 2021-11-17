package custom.basic.api.feign;

import common.core.constant.SecurityConstants;
import common.core.constant.ServiceNameConstants;
import common.core.util.R;
import custom.basic.api.dto.CaptchaImageDTO;
import custom.basic.api.dto.SmsCodeDTO;
import custom.basic.api.feign.factory.RemoteSmsCodeServiceFallbackFactory;
import custom.basic.api.vo.SmsCodeVO;
import custom.basic.api.vo.SmsMemberVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author asd <br>
 * @create 2021-06-29 5:35 PM <br>
 * @project custom-upms-grpc <br>
 */
@FeignClient(
        value = ServiceNameConstants.UMPS_SERVICE,
        fallbackFactory = RemoteSmsCodeServiceFallbackFactory.class)
public interface RemoteSmsCodeService {

    @PostMapping("/sms-code")
    R<SmsCodeVO> sendMessage(CaptchaImageDTO captchaImageDTO);

    @PostMapping("/inner/sms-code/check")
    R<SmsMemberVO> validateUser(
            @RequestBody SmsCodeDTO smsCode, @RequestHeader(SecurityConstants.FROM) String from);
}
