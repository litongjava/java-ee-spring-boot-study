package com.litongjava.spring.boot.beetl.sql.demo.model;

import lombok.Data;

@Data
public class User {
  private Long id;
  private String name;
  private Integer age;
  private String email;
  private Integer departmentId;
}