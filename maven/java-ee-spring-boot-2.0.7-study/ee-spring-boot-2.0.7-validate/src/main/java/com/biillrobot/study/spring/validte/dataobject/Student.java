package com.biillrobot.study.spring.validte.dataobject;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Student {
  @NotBlank(message = "用户名不能为空")
  private String name;
  @Max(value = 120, message = "年龄不能超过120岁")
  private int age;
  @NotNull
  @Size(min = 8, max = 20, message = "密码必须大于8位并且小于20位")
  private String password;
  @Email(message = "请输入符合格式的邮箱")
  private String email;
}
