package com.litong.study.spring.boot.v158.excel.entity;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author litong
 * @since 2020-06-04
 */
public class User implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 主键ID
   */

  @ExcelProperty(value = "主键ID", index = 0)
  private Long id;

  /**
   * 姓名
   */
  @ExcelProperty(value = "姓名", index = 1)
  private String name;

  /**
   * 年龄
   */
  @ExcelProperty(value = "年龄", index = 2)
  private Integer age;

  /**
   * 邮箱
   */
  @ExcelProperty(value = "邮箱", index = 3)
  private String email;

  /**
   * 地址
   */
  @ExcelProperty(value = "地址", index = 4)
  private String addr;

  /**
   * 备注
   */
  @ExcelProperty(value = "备注", index = 4)
  private String remark;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name=" + name + ", age=" + age + ", email=" + email + ", addr=" + addr + ", remark="
        + remark + "}";
  }
}
