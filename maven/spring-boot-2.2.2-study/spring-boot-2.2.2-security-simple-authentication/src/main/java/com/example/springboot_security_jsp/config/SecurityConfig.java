package com.example.springboot_security_jsp.config;

import com.example.springboot_security_jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author john
 * @date 2020/1/11 - 19:17
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // 认证用户的来源(内存或者数据库)
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
  }

  // 配置springsecurity相关信息
  protected void configure(HttpSecurity http) throws Exception {
    // 释放静态资源，指定资源拦截规则，指定自定义认证页面，指定退出认证配置，csrf配置
    ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests = http
        .authorizeRequests();
    authorizeRequests.antMatchers("/login.jsp", "/failer.jsp", "/css/**", "/img/**", "/plugins/**").permitAll();
    authorizeRequests.antMatchers("/**").hasAnyRole("USER", "ADMIN");
    authorizeRequests.anyRequest().authenticated();
    
    FormLoginConfigurer<HttpSecurity> formLogin = authorizeRequests.and().formLogin();
    formLogin.loginPage("/login.jsp").loginProcessingUrl("/login");
    formLogin.successForwardUrl("/index.jsp").failureForwardUrl("/failer.jsp").permitAll();
    
    LogoutConfigurer<HttpSecurity> logout = formLogin.and().logout();
    logout.logoutUrl("/logout").logoutSuccessUrl("/login.jsp").invalidateHttpSession(true).permitAll();
    // 禁用csrf
//                .and()
//                .csrf()
//                .disable();
  }

}
