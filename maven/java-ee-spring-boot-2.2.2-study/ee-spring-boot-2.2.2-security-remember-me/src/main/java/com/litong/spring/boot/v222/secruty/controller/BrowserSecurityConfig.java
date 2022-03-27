package com.litong.spring.boot.v222.secruty.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.RememberMeConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * 配置 spring-scecrity 
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  ValidateCodeFilter validateCodeFilter;
  @Autowired
  MyAuthenticationFailureHandler myAuthenticationFailureHandler;
  @Autowired
  private DataSource dataSource;
  @Autowired
  private MyUserDetailsService userDetailsService;

  // 手动将PasswordEncoder注入到ioc容器中
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // 1. 配置TokenRepository
  @Bean
  public PersistentTokenRepository persistentTokenRepository() {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
    tokenRepository.setCreateTableOnStartup(true);
    return tokenRepository;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    validateCodeFilter.setMyAuthenticationFailureHandler(myAuthenticationFailureHandler);

    // 将验证码过滤器配置到UsernamePasswordAuthenticationFilter前面
    http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
    // 表单登录
    FormLoginConfigurer<HttpSecurity> formLogin = http.formLogin();
    // 设置登录路由 设置登录处理url
    formLogin.loginPage("/signin.html").loginProcessingUrl("/auth/form");
    // 设置失败处理
    formLogin.failureHandler(myAuthenticationFailureHandler);

    // 记住我的配置
    // rememberMe需要的配置包含TokenRepository对象以及token过期时间
    RememberMeConfigurer<HttpSecurity> rememberMe = formLogin.and().rememberMe();
    rememberMe.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60 * 60 * 24);
    rememberMe.userDetailsService(userDetailsService);

    // 身份认证设置
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = rememberMe.and()
        .authorizeRequests();

    // 该路由不需要身份认账
    authorizeRequests.antMatchers("/signin.html").permitAll();
    // 该路由不需要身份认账
    authorizeRequests.antMatchers("/code/*").permitAll();
    // 其他的路由均需要身份认证
    authorizeRequests.anyRequest().authenticated();

    // 先禁用防止跨站脚本攻击的csrf token
    CsrfConfigurer<HttpSecurity> csrf = authorizeRequests.and().csrf();
    csrf.disable();
  }
}