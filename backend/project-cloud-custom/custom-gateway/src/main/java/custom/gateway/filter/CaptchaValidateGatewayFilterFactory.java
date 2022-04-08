package custom.gateway.filter;

import javax.annotation.Resource;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.core.configuration.properties.SecurityIgnoreProperties;
import common.core.constant.SecurityConstants;
import common.core.constant.enums.BusinessResponseEnum;
import common.core.util.R;
import common.redis.constants.enums.RedisKeyCommonEnum;
import common.redis.utils.RedisUtil;
import custom.gateway.constants.AuthConstants;
import custom.gateway.constants.CacheKeys;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

/**
 * @author zack <br>
 * @create 2021-06-26<br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Component
public class CaptchaValidateGatewayFilterFactory extends AbstractGatewayFilterFactory {
    @Resource private ObjectMapper objectMapper;

    @Resource private SecurityIgnoreProperties securityIgnoreProperties;

    @Resource private RedisUtil redisUtil;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(
                    request.getURI().getPath(), AuthConstants.URL_OAUTH_TOKEN)) {
                return chain.filter(exchange);
            }

            // only password grant type check captcha code.
            // password only for admin login.
            String grantType = request.getQueryParams().getFirst("grant_type");
            if (!StrUtil.equals(SecurityConstants.GRANT_TYPE_PASSWORD, grantType)) {
                return chain.filter(exchange);
            }

            try {
                // 校验验证码
                checkCode(request);
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                try {
                    R result = R.builder().code(1).data(e.getMessage()).build();
                    return response.writeWith(
                            Mono.just(
                                    response.bufferFactory()
                                            .wrap(objectMapper.writeValueAsBytes(result))));
                } catch (JsonProcessingException e1) {
                    log.error("write json exception", e1);
                }
            }

            return chain.filter(exchange);
        };
    }

    /**
     * 检查code
     *
     * @param request
     */
    @SneakyThrows
    private void checkCode(ServerHttpRequest request) {
        String code = request.getQueryParams().getFirst("code");

        if (StrUtil.isBlank(code)) {
            BusinessResponseEnum.VERIFY_CODE_ERROR.assertFailWithMsg("验证码参数错误");
        }

        String captchaKey = request.getQueryParams().getFirst(AuthConstants.PARAM_CAPTCHA_KEY);
        if (StrUtil.isBlank(captchaKey)) {
            BusinessResponseEnum.VERIFY_CODE_ERROR.assertFailWithMsg("验证码不能为空");
        }

        String key = CacheKeys.CAPTCHA_CODE_KEY_PREFIX + captchaKey;
        if (!redisUtil.hasKey(key)) {
            BusinessResponseEnum.VERIFY_CODE_ERROR.assertFailWithMsg("验证码不合法");
        }

        Object codeObj = redisUtil.get(RedisKeyCommonEnum.VERIFY_CODE, Object.class, key);
        if (codeObj == null) {
            BusinessResponseEnum.VERIFY_CODE_ERROR.assertFailWithMsg("验证码不合法");
        }

        String saveCode = codeObj.toString();
        if (StrUtil.isBlank(saveCode)) {
            redisUtil.remove(RedisKeyCommonEnum.VERIFY_CODE, key);
            BusinessResponseEnum.VERIFY_CODE_ERROR.assertFailWithMsg("验证码不合法");
        }

        if (!StrUtil.equalsIgnoreCase(saveCode, code)) {
            redisUtil.remove(RedisKeyCommonEnum.VERIFY_CODE, key);
            BusinessResponseEnum.VERIFY_CODE_ERROR.assertFailWithMsg("验证码不合法");
        }

        redisUtil.remove(RedisKeyCommonEnum.VERIFY_CODE, key);
    }
}
