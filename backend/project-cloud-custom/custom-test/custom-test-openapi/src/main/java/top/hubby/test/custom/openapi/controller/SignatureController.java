package top.hubby.test.custom.openapi.controller;

import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author zack <br/>
 * @create 2022-04-08 20:38 <br/>
 * @project project-cloud-custom <br/>
 */
@RestController
@Slf4j
@RequestMapping("/openapi")
@Api(value = "第三方接口模块", tags = "第三方接口模块")
public class SignatureController {

    @LimitRequest(count = 100)
    @PostMapping("/signature")
    public R<String> getSignature(
            @RequestHeader(RequestConstants.NONCESTR) String noncestr,
            @RequestHeader(RequestConstants.TIMESTAMP) Long timestamp,
            @RequestHeader(RequestConstants.URI) String uri,
            @RequestHeader(APPID) String appId,
            @RequestBody String body) {

        return new R<>(
                McSecureUtil.generateSignature(
                        uri, body, timestamp, noncestr, appId, APP_MAP.get(appId)));
    }
}
