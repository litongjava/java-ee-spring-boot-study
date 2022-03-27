package com.litong.spring.boot.mybatis.tk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统角色
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole {
  private Integer id;
  private String roleName;
  private String roleDesc;
}
