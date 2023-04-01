package com.litongjava.spring.boot.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litongjava.spring.boot.bo.UserBo;

@RestController
@RequestMapping("")
public class IndexController {
  @RequestMapping("")
  public String index() {
    return "index";
  }

  @RequestMapping("echo")
  public UserBo echo(UserBo userBo) {
    return userBo;
  }

  @GetMapping("/receiveLocalDate")
  public LocalDate receiveLocalDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthday) {
    return birthday;
  }
}