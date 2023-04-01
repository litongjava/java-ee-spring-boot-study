package com.litong.spring.boot.v222.secruty.controller;

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

import lombok.extern.slf4j.Slf4j;

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
    // User类是Spring内置的一个类，实现了UserDetails接口，而这个接口是UserDetailSerice的子接口
    List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
    User user = new User(username, password, true, true, true, true, authorityList);
    return user;
  }
}