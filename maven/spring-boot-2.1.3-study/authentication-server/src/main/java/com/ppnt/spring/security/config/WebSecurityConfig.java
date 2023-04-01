package com.ppnt.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ppnt.spring.security.filter.JwtLoginFilter;
import com.ppnt.spring.security.service.UserService;

import top.ppnt.spring.boot.config.RsaKeyProperties;

@Configuration
@EnableWebSecurity // 加了这个注解才能写SpringSecurity相关的配置
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserService userService;

  private final RsaKeyProperties rsaKeyProperties;

  public WebSecurityConfig(UserService userService, RsaKeyProperties rsaKeyProperties) {
    this.userService = userService;
    this.rsaKeyProperties = rsaKeyProperties;
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 认证用户的来源
   *
   * @param authenticationManagerBuilder
   * @throws Exception
   */
  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    // userDetailsService
    DaoAuthenticationConfigurer<AuthenticationManagerBuilder, UserService> configurer = authenticationManagerBuilder
        .userDetailsService(userService);
    // passwordEncoder
    configurer.passwordEncoder(passwordEncoder());
  }

  /**
   * 配置SpringSecurity相关信息
   *
   * @param http
   * @throws Exception
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    JwtLoginFilter filter = new JwtLoginFilter(super.authenticationManager(), rsaKeyProperties);

    // 获取Csrf配置类
    CsrfConfigurer<HttpSecurity> csrfConfig = http.csrf();
    // 关闭csrf
    HttpSecurity httpSecurity = csrfConfig.disable();
    // 添加拦截器
    httpSecurity.addFilter(filter);
    // 获取SessionManager
    SessionManagementConfigurer<HttpSecurity> sessionManagement = httpSecurity.sessionManagement();
    // 禁用session
    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

}
