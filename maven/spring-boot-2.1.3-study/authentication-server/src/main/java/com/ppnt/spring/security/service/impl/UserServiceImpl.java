package com.ppnt.spring.security.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ppnt.spring.security.service.UserService;

import top.ppnt.spring.boot.mapper.UserMapper;
import top.ppnt.spring.boot.model.SysUser;

@Service("userService")
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  public UserServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SysUser sysUser = userMapper.findByUsername(username);
    return sysUser;
  }
}