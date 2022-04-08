package top.hubby.test.custom.openapi.controller;

import common.core.constant.RequestConstants;
import common.core.util.R;
import common.redis.annotation.RedisLimitRequest;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import top.hubby.openapi.util.OpenApiSecureUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static common.core.constant.RequestConstants.APPID;
import static top.hubby.openapi.component.OpenApiSecretComponent.APP_MAP;

/**
 * @author zack <br>
 * @create 2022-04-08 20:38 <br>
 * @project project-cloud-custom <br>
 */
@RestController
@Slf4j
@RequestMapping("/openapi")
@Api(value = "第三方接口模块", tags = "第三方接口模块")
public class SignatureController {

    @RedisLimitRequest(count = 100)
    @PostMapping("/signature")
    public R<String> getSignature(
            @RequestHeader(RequestConstants.NONCESTR) String noncestr,
            @RequestHeader(RequestConstants.TIMESTAMP) Long timestamp,
            @RequestHeader(RequestConstants.URI) String uri,
            @RequestHeader(APPID) String appId,
            @RequestBody String body) {

        return new R<>(
                OpenApiSecureUtil.generateSignature(
                        uri, body, timestamp, noncestr, appId, APP_MAP.get(appId)));
    }
}
