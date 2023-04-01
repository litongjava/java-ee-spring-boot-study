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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 配置spring-security
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  ValidateCodeFilter validateCodeFilter;
  @Autowired
  MyAuthenticationFailureHandler myAuthenticationFailureHandler;

  // 手动将PasswordEncoder注入到ioc容器中
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    validateCodeFilter.setMyAuthenticationFailureHandler(myAuthenticationFailureHandler);

    // 将验证码过滤器配置到UsernamePasswordAuthenticationFilter前面
    http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
    // 表单登录
    FormLoginConfigurer<HttpSecurity> formLogin = http.formLogin();
    // 登录界面和登录接口
    formLogin.loginPage("/signin.html").loginProcessingUrl("/auth/form");
    // 设置登录失败处理
    formLogin.failureHandler(myAuthenticationFailureHandler);
    // 配置身份认证
    HttpSecurity and = formLogin.and();
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = and
        .authorizeRequests();
    // 该路由不需要身份认账
    authorizeRequests.antMatchers("/signin.html").permitAll();
    authorizeRequests.antMatchers("/code/*").permitAll();
    // 其他的路由均需要身份认证
    authorizeRequests.anyRequest().authenticated();
    // 先禁用防止跨站脚本攻击的csrf token
    authorizeRequests.and().csrf().disable();
  }

}