package com.litong.spring.boot.v222.secruty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 配置自定义登录
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
  @Autowired
  MyAuthenticationFailureHandler myAuthenticationFailureHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 表单登录
    FormLoginConfigurer<HttpSecurity> formLogin = http.formLogin();
    formLogin.loginPage("/auth/require"); // 设置登录路由,检测到用户没有登录调整到此处
    formLogin.loginProcessingUrl("/auth/form"); // 设置登录处理url
    // 设置自定义成功处理器,设置自定义失败处理器
    formLogin.successHandler(myAuthenticationSuccessHandler).failureHandler(myAuthenticationFailureHandler);

    HttpSecurity and = formLogin.and();
    // 身份认证设置
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = and
        .authorizeRequests();
    // 该路由不需要身份认账
    authorizeRequests.antMatchers("/signin.html").permitAll();
    // 该路由不需要身份认账
    authorizeRequests.antMatchers("/auth/*").permitAll();
    // 其他的路由均需要身份认证
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authenticated = authorizeRequests
        .anyRequest().authenticated();

    // 先禁用防止跨站脚本攻击的csrf token
    authenticated.and().csrf().disable();
  }
}