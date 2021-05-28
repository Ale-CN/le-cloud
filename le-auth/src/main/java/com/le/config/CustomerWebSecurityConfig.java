package com.le.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

@Configuration
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法权限注解功能
@Order(101)
public class CustomerWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("le").password("1").roles("admin")
                .and()
                .withUser("zs").password("1").roles("user");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Override
//    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 注意：此处配置的路径不经过拦截器
        web.ignoring().antMatchers("/user/getToken", "/oauth/token_key");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置策略,比如防止csrf攻击,根据需求,不配就使用默认的也行
        http.csrf().disable()
                .httpBasic().and()
                .formLogin()
//                .loginProcessingUrl("/index")
                .successHandler((req, resp, auth) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    try (PrintWriter out = resp.getWriter()) {
                        out.println("登陆成功！");
                    }
                })
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
