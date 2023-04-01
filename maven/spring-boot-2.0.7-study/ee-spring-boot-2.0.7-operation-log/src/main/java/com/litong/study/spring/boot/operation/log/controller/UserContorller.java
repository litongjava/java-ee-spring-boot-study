package com.litong.study.spring.boot.operation.log.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litong.study.spring.boot.operation.log.annotation.SystemControllerLog;
import com.litong.study.spring.boot.operation.log.bean.User;
import com.litong.study.spring.boot.operation.log.servie.UserService;

@RestController
@RequestMapping("user")
public class UserContorller {

  @Autowired
  private UserService userService;

  @RequestMapping("get")
  @SystemControllerLog(description = "获取用户")
  public User getUser() {
    return userService.getUser();
  }
}
