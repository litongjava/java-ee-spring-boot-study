package com.litongjava.study.spring.boot.elmentui.entity;

import java.io.Serializable;

/**
 * (Emp)实体类
 *
 * @author litongjava
 * @since 2023-04-01 18:12:15
 */
public class Emp implements Serializable {
  private static final long serialVersionUID = -42251563480339962L;

  private Integer id;

  private String name;

  private Double salary;

  private Integer age;

  private String email;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getSalary() {
    return salary;
  }

  public void setSalary(Double salary) {
    this.salary = salary;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
