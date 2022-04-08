package top.hubby.test.custom.http.service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.naming.OperationNotSupportedException;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import common.http.constant.enums.RedisHttpEnum;
import common.http.model.HttpProperties;
import common.http.model.TokenVO;
import common.http.service.BusinessService;
import common.http.support.HttpSupport;
import common.redis.utils.RedisUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import static common.http.constant.Constants.API_AUTH_FLAG;
import static common.http.constant.Constants.CLIENT_CREDENTIALS;
import static common.http.constant.Constants.CLIENT_ID;
import static common.http.constant.Constants.CLIENT_SECRET;
import static common.http.constant.Constants.GRANT_TYPE;
import static common.http.constant.Constants.URL_ACCESS_TOKEN;

/**
 * @author asd <br>
 * @create 2021-12-07 4:55 PM <br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Component
@Primary
public class BusinessServiceImpl extends BusinessService {

    @Resource private HttpProperties httpProperties;
    @Resource private RedisUtil redisUtil;
    @Resource private ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public void refreshToken() {
        throw new OperationNotSupportedException();
    }

    @SneakyThrows
    @Override
    public TokenVO token() {
        Map<String, String> params =
                ImmutableMap.<String, String>builder()
                        .put(GRANT_TYPE, CLIENT_CREDENTIALS)
                        .put(CLIENT_ID, httpProperties.getClientId())
                        .put(CLIENT_SECRET, httpProperties.getClientSecret())
                        .build();

        String postUrl = httpProperties.getHost() + URL_ACCESS_TOKEN;
        Map<String, String> headers = ImmutableMap.of(API_AUTH_FLAG, Boolean.TRUE.toString());
        String response = HttpSupport.doPost(postUrl, params, headers);
        TokenVO token = objectMapper.readValue(response, TokenVO.class);
        if (token != null && StrUtil.isNotEmpty(token.getAccessToken())) {
            redisUtil.set(
                    RedisHttpEnum.TOKEN_KEY,
                    token.getAccessToken(),
                    token.getExpiresIn(),
                    TimeUnit.SECONDS);
        }

        return token;
    }

    @SneakyThrows
    @Override
    public void refreshDecryptToken() {
        throw new OperationNotSupportedException();
    }
}
