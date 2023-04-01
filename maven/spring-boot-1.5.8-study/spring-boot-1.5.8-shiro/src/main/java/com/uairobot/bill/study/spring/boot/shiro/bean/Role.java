package com.uairobot.bill.study.spring.boot.shiro.bean;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Role {
  private String id;
  private String roleName;
  /**
   * 角色对应权限集合
   */
  private Set<Permissions> permissions;
}