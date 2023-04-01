package com.litongjava.spring.boot.jfinal.active.record.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
  private Long id;
  private String suername;

}
