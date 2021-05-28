package com.le.config.auth;

import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

import javax.annotation.Resource;

@Configuration
public class MyAuthorizationServerConfig implements AuthorizationServerConfigurer {

    @Resource
    KeyProperties keyProperties;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) throws Exception {

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client
                // 使用in‐memory存储
                .inMemory()
                // client_id
                .withClient("c1")
                //客户端密钥
                .secret(new BCryptPasswordEncoder().encode("secret"))
                //客户端访问的资源列表
                .resourceIds("res1")
                // 该client允许的授权类型 authorization_code,password,refresh_token,implicit,client_credentials
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")
                // 允许的授权范围
                .scopes("all")
                //false 允许跳转到授权页面
                .autoApprove(false)
                //加上验证回调地址
                .redirectUris("http://www.baidu.com");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) throws Exception {

    }
}
