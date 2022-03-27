package com.uairobot.bill.study.spring.boot.shiro.bean;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private String id;
  private String username;
  private String password;
  /**
   * 用户对应的角色集合
   */
  private Set<Role> roles;
}