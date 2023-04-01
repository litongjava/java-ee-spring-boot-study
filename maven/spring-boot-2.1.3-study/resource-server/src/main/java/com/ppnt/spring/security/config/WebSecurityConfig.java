package com.ppnt.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.ppnt.spring.security.filter.JwtVerifyFilter;

import top.ppnt.spring.boot.config.RsaPublicKeyProperties;

@Configuration
//加了这个注解才能写SpringSecurity相关的配置
@EnableWebSecurity 
//开启权限控制的注解支持,securedEnabled表示SpringSecurity内部的权限控制注解开关
@EnableGlobalMethodSecurity(securedEnabled = true) 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final RsaPublicKeyProperties rsaKeyProperties;

  public WebSecurityConfig(RsaPublicKeyProperties rsaKeyProperties) {
    this.rsaKeyProperties = rsaKeyProperties;
  }

  /**
   * 配置SpringSecurity相关信息
   *
   * @param http
   * @throws Exception
   */
  @Override
  public void configure(HttpSecurity http) throws Exception {
    JwtVerifyFilter filter = new JwtVerifyFilter(super.authenticationManager(), rsaKeyProperties);
    http.csrf().disable() // 关闭csrf
        .authorizeRequests().antMatchers("/**").hasAnyRole("USER") // 角色信息
        .anyRequest() // 其它资源
        .authenticated() // 表示其它资源认证通过后
        .and().addFilter(filter).sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用session
  }

}
