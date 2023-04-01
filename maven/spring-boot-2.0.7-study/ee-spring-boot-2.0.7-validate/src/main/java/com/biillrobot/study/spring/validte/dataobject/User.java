package com.biillrobot.study.spring.validte.dataobject;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.biillrobot.study.spring.validte.annotation.MustEmpty;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class User {
  public interface Update{};
  public interface Insert{};
  @NotNull(groups=Update.class ,message="更新时id不能为空")
  @MustEmpty(groups=Insert.class,message="添加时id必须为空")
  private String id;
  
  private String name;
  @Past(message = "{user.birthday.past}")
  @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy/MM/dd")
  private Date birthday;
}