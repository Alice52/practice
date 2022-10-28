package custom.gateway.filter;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpUtil;
import custom.gateway.constants.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import static cn.hutool.core.util.CharsetUtil.CHARSET_UTF_8;

/**
 * @author zack <br>
 * @create 2021-06-26<br>
 * @project project-cloud-custom <br>
 */
@Slf4j
@Component
public class PasswordDecoderGatewayFilterFactory extends AbstractGatewayFilterFactory {
    private static final String OAUTH_PARAM_PASSWORD = "password";
    private static final String OAUTH_PARAM_GRANT_TYPE = "grant_type";

    private static final String KEY_ALGORITHM = "AES";

    @Value("${security.encode.key:1234567812345678}")
    private String encodeKey;

    private static String decryptAES(String data, String pass) {
        AES aes =
                new AES(
                        Mode.CBC,
                        Padding.NoPadding,
                        new SecretKeySpec(pass.getBytes(), KEY_ALGORITHM),
                        new IvParameterSpec(pass.getBytes()));
        byte[] result = aes.decrypt(Base64.decode(data.getBytes(StandardCharsets.UTF_8)));
        return byteToStr(result);
    }

    private static String byteToStr(byte[] buffer) {
        int length = buffer.length;
        for (int i = buffer.length - 1; i >= 0; i--) {
            if (buffer[i] != 0) {
                length = i + 1;
                break;
            }
        }
        return new String(buffer, 0, length, StandardCharsets.UTF_8);
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 不是登录请求，直接向下执行
            if (!StrUtil.containsAnyIgnoreCase(
                    request.getURI().getPath(), AuthConstants.URL_OAUTH_TOKEN)) {
                return chain.filter(exchange);
            }

            URI uri = exchange.getRequest().getURI();
            String queryParam = uri.getRawQuery();
            Map<String, String> paramMap = HttpUtil.decodeParamMap(queryParam, CHARSET_UTF_8);

            String password = paramMap.get(OAUTH_PARAM_PASSWORD);
            if (StrUtil.isNotBlank(password)) {
                try {
                    password = decryptAES(password, encodeKey);
                } catch (Exception e) {
                    log.error("密码解密失败:{}", password);
                    return Mono.error(e);
                }
                paramMap.put(OAUTH_PARAM_PASSWORD, password);
            }

            URI newUri =
                    UriComponentsBuilder.fromUri(uri)
                            .replaceQuery(HttpUtil.toParams(paramMap))
                            .build(true)
                            .toUri();

            ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(newRequest).build());
        };
    }
}
