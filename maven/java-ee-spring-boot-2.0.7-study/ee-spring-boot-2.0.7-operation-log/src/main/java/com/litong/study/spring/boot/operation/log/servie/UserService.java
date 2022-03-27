package com.litong.study.spring.boot.operation.log.servie;

import org.springframework.stereotype.Service;

import com.litong.study.spring.boot.operation.log.annotation.SystemServiceLog;
import com.litong.study.spring.boot.operation.log.bean.User;

@Service
public class UserService {
  @SystemServiceLog(description = "查询用户")
  public User getUser() {
    User user = new User();
    user.setId("001").setUsername("litong");
    return user;
  }
}
