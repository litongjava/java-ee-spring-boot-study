package com.uairobot.bill.study.spring.boot.shiro.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
  @ExceptionHandler
  @ResponseBody
  public String errorHandler(AuthorizationException e) {
    log.error("没有通过权限验证！", e);
    return "没有通过权限验证！";
  }
}