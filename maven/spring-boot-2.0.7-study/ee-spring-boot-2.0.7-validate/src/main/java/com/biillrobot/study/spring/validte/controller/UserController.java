package com.biillrobot.study.spring.validte.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biillrobot.study.spring.validte.dataobject.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("user")
@Slf4j
@Validated
public class UserController {
  @RequestMapping("add")
  // public String add(@Valid User user, BindingResult result) {
  public String add(@Validated(User.Insert.class) User user, BindingResult bindingResult) {
    log.info("user={}", user);
    if (bindingResult.hasErrors()) {
      String errorMsg = bindingResult.getFieldError().getDefaultMessage();
      log.info("errorMsg={}", errorMsg);
      return errorMsg;
    }
    return "success";
  }

  @RequestMapping("update")
  public String update(@Validated({ User.Update.class }) User user, BindingResult bindingResult) {
    log.info("user={}", user);
    if (bindingResult.hasErrors()) {
      String errorMsg = bindingResult.getFieldError().getDefaultMessage();
      log.info("errorMsg={}", errorMsg);
      return errorMsg;
    }
    return "success";
  }
}
