package com.litongjava.spring.boot.sharding.jdbc.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litongjava.spring.boot.sharding.jdbc.user.entity.User;
import com.litongjava.spring.boot.sharding.jdbc.user.service.impl.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
  private final UserServiceImpl userService;

  @GetMapping("/select")
  public List<User> select() {
    return userService.list();
  }

  @GetMapping("/insert")
  public boolean insert() {
    User build = User.builder().name("name3").build();
    return userService.saveOrUpdate(build);
  }
}
