package custom.auth.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import common.core.constant.SecurityConstants;
import common.security.component.CustomWebResponseExceptionTranslator;
import common.security.grant.MobileTokenGranter;
import common.security.model.CustomUser;
import common.security.service.AuthUserDetailsService;
import common.security.service.CustomJdbcClientDetailsService;
import custom.basic.api.feign.RemoteSmsCodeService;
import custom.basic.api.feign.RemoteUserService;
import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author asd <br>
 * @create 2021-06-30 5:00 PM <br>
 * @project custom-upms-grpc <br>
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource private DataSource dataSource;
    @Resource private RedisConnectionFactory redisConnectionFactory;
    @Resource private AuthenticationManager authenticationManager;
    @Resource private RemoteSmsCodeService remoteSmsCodeService;
    @Resource private RemoteUserService upmsRemoteUserService;
    @Resource private RedisTokenStore redisTokenStore;

    @Bean
    @Qualifier("auth")
    public UserDetailsService userDetailsService() {
        return new AuthUserDetailsService();
    }

    @Override
    @SneakyThrows
    public void configure(ClientDetailsServiceConfigurer clients) {
        CustomJdbcClientDetailsService clientDetailsService =
                new CustomJdbcClientDetailsService(dataSource);
        clientDetailsService.setSelectClientDetailsSql(SecurityConstants.DEFAULT_SELECT_STATEMENT);
        clientDetailsService.setFindClientDetailsSql(SecurityConstants.DEFAULT_FIND_STATEMENT);
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.allowFormAuthenticationForClients().checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenStore(redisTokenStore)
                .tokenEnhancer(tokenEnhancer())
                .userDetailsService(userDetailsService())
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false)
                .exceptionTranslator(new CustomWebResponseExceptionTranslator());

        MobileTokenGranter mobileTokenGranter =
                new MobileTokenGranter(
                        endpoints.getTokenServices(),
                        endpoints.getClientDetailsService(),
                        endpoints.getOAuth2RequestFactory(),
                        remoteSmsCodeService);

        CompositeTokenGranter compositeTokenGranter =
                new CompositeTokenGranter(
                        Arrays.asList(endpoints.getTokenGranter(), mobileTokenGranter));
        endpoints.tokenGranter(compositeTokenGranter);
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return (accessToken, authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(2);
            Authentication userAuthentication = authentication.getUserAuthentication();
            if (userAuthentication != null) {
                CustomUser user = (CustomUser) userAuthentication.getPrincipal();
                if (user != null) {
                    additionalInfo.put("userType", user.getUserType());
                    additionalInfo.put("basicInfo", user.getBasicInfo());
                }
            }

            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
            return accessToken;
        };
    }
}
