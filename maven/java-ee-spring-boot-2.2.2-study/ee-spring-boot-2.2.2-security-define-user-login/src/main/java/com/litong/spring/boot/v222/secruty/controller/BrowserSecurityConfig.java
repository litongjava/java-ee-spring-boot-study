package com.litong.spring.boot.v222.secruty.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // 表单登录
    // http.formLogin()
    // 基本登录
    http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
//    HttpBasicConfigurer<HttpSecurity> httpBasic = http.httpBasic();
//    HttpSecurity and = httpBasic.and();
//    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = and.authorizeRequests();
//    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl anyRequest = authorizeRequests.anyRequest();
//    @SuppressWarnings("unused")
//    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authenticated = anyRequest.authenticated();
  }
}
