package com.litong.spring.boot.v222.secruty.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义用户认证逻辑
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
  // 这里可以注入mapper或者repository的dao对象来实现数据校验逻辑操作
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("用户名：" + username);
    // 这里密码应该从数据库中取出,暂时先使用加密生成
    String password = passwordEncoder.encode("123456");
    List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
    return new User(username, password, true, true, true, true, authorityList);
  }
}