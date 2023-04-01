package com.example.springboot_security_jsp.domain;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * 系统角色
 */
@SuppressWarnings("serial")
@Data
public class SysRole implements GrantedAuthority {
  private Integer id;
  private String roleName;
  private String roleDesc;

  // 标记此属性不做json处理
  @JsonIgnore
  @Override
  public String getAuthority() {
    return roleName;
  }
}
