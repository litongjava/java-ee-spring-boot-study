package com.ppnt.spring.boot.demo.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class HelloRespDTO implements Serializable {

  private String name;

  private String address;

  private Integer age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}