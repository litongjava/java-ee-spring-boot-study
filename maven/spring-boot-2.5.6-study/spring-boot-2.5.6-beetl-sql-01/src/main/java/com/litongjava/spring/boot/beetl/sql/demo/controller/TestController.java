package com.litongjava.spring.boot.beetl.sql.demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.query.LambdaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.litongjava.spring.boot.beetl.sql.demo.dao.UserDao;
import com.litongjava.spring.boot.beetl.sql.demo.model.User;

@RestController
public class TestController {

  @Autowired
  private SQLManager sqlManager;

  @Resource
  private UserDao userMapper;

  @GetMapping(value = "/users/{name}")
  public List<User> users(@PathVariable String name) {
    return userMapper.findByName(name);
  }

  @GetMapping(value = "/users2/{name}")
  public List<User> usersBySQLManager(@PathVariable String name) {
    // 获取lambdaQuery
    LambdaQuery<User> lambdaQuery = sqlManager.lambdaQuery(User.class);
    // 添加条件
    lambdaQuery.andEq(User::getName, name);
    // 获取数据
    List<User> data = lambdaQuery.select("id", "name", "age", "email");
    return data;
  }
}