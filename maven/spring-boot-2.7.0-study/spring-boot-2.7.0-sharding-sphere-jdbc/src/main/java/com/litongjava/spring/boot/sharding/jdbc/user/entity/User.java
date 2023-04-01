package com.litongjava.spring.boot.sharding.jdbc.user.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2022-10-02
 */
@Getter
@Setter
@Builder
public class User {

  /**
   * 主键ID
   */
  private Long id;

  /**
   * 姓名
   */
  private String name;

  /**
   * 年龄
   */
  private Integer age;

  /**
   * 邮箱
   */
  private String email;

  /**
   * 地址
   */
  private String addr;

  /**
   * 备注
   */
  private String remark;

}
