package com.jjpeng.resourceserver.config;

import com.jjpeng.resourceserver.tokenconverter.AdditionalClaimsAccessTokenConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author JJPeng
 * @date 2022/7/24 17:42
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${publicKey}")
    private String publicKey;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(
                jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new AdditionalClaimsAccessTokenConverter();
        jwtAccessTokenConverter.setVerifierKey(publicKey);
        return jwtAccessTokenConverter;
    }
}
