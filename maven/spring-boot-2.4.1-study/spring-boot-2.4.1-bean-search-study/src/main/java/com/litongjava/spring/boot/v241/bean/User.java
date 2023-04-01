package com.litongjava.spring.boot.v241.bean;

import java.util.Date;

import com.ejlchina.searcher.bean.DbField;
import com.ejlchina.searcher.bean.SearchBean;

import lombok.Data;

@SearchBean(tables = "user u, role r", joinCond = "u.role_id = r.id", autoMapTo = "u")
@Data
public class User {
  private long id;
  private String username;
  private int status;
  private int age;
  private String gender;
  private Date joinDate;
  private int roleId;
  @DbField("r.name")
  private String roleName;
}
